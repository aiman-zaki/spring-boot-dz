package com.github.aimanzaki.springbootdz.repositories

import com.github.aimanzaki.springbootdz.models.Stock
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.data.repository.query.Param
import java.util.UUID

interface StockRepository : PagingAndSortingRepository<Stock, UUID> {

    @Query(
        "SELECT s FROM Stock s WHERE CAST(branchId as org.hibernate.type.UUIDCharType) LIKE CONCAT('%',:branchId,'%') " +
            "AND CAST(id as org.hibernate.type.UUIDCharType) LIKE CONCAT('%',:id,'%') " +
            "AND function('to_char',stockDate,'YYYY-MM-DD') LIKE CONCAT('%',:stockDate,'%')"
    )
    fun findAllWithFilterAndPageable(
        @Param("id") id: String?,
        @Param("branchId") branchId: String?,
        @Param("stockDate") stockDate: String,
        pageable: Pageable,
    ): Page<Stock>

    @Query("select s from Stock s where function('to_char',s.stockDate,'MM') LIKE CONCAT('%',?1,'%') and s.branchId = ?2")
    fun findByStockDateAndBranchId(yearMonth: String, branchId: UUID): Stock?
}
