package com.github.aimanzaki.springbootdz.repositories

import com.github.aimanzaki.springbootdz.models.Stock
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.data.repository.query.Param

interface StockRepository : PagingAndSortingRepository<Stock, Long> {

    @Query(
        "SELECT s FROM Stock s WHERE CAST(branchId as org.hibernate.type.UUIDCharType) LIKE CONCAT('%',:branchId,'%') " +
            "AND CAST(id as org.hibernate.type.UUIDCharType) LIKE CONCAT('%',:id,'%')"
    )
    fun findAllWithFilterAndPageable(
        @Param("id") id: String?,
        @Param("branchId") branchId: String?,
        pageable: Pageable,
    ): Page<Stock>
}
