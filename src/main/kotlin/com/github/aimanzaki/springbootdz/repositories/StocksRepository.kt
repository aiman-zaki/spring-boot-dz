package com.github.aimanzaki.springbootdz.repositories

import com.github.aimanzaki.springbootdz.models.Stock
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.data.repository.query.Param

interface StocksRepository : PagingAndSortingRepository<Stock, Long> {

    @Query("SELECT * FROM stocks s WHERE s.branch_id = COALESCE(:branchId, s.branch_id)", nativeQuery = true)
    fun findAll(@Param("branchId") branchId: Long?, pageable: Pageable): Page<Stock>

    @Query("SELECT * FROM stocks s WHERE to_char(s.created_on, 'YYYY')= :year AND s.branch_id = COALESCE(:branchId, s.branch_id)", nativeQuery = true)
    fun findStocksByYear(@Param("branchId") branchId: Long?, @Param("year") year: String, pageable: Pageable): Page<Stock>

    @Query("SELECT * FROM stocks s WHERE to_char(s.created_on, 'MM/YYYY')= :yearMonth AND s.branch_id = COALESCE(:branchId, s.branch_id)", nativeQuery = true)
    fun findStocksByYearAndMonth(@Param("branchId") branchId: Long?, @Param("yearMonth") yearMonth: String, pageable: Pageable): Page<Stock>

    @Query("SELECT * FROM stocks s WHERE to_char(s.created_on, 'DD/MM/YYYY')= :date AND s.branch_id = COALESCE(:branchId, s.branch_id)", nativeQuery = true)
    fun findStocksByDate(@Param("branchId") branchId: Long?, @Param("date") date: String, pageable: Pageable): Page<Stock>
}
