package com.github.aimanzaki.springbootdz.repositories

import com.github.aimanzaki.springbootdz.models.StockWithDetails
import com.github.aimanzaki.springbootdz.models.StockWithDetailsId
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import java.util.UUID

interface StockWithDetailsRepository : JpaRepository<StockWithDetails, StockWithDetailsId> {
    fun findByStockId(stockId: UUID): List<StockWithDetails>
    fun findByIdProductCode(productCode: String): List<StockWithDetails>

    @Query(
        "SELECT swd FROM StockWithDetails swd WHERE id.productCode = :productCode AND " +
            "function('to_char',stockDate,'YYYY-MM-DD') = :stockDate"
    )
    fun findByStockDateAndProductCode(
        @Param("productCode") productCode: String,
        @Param("stockDate") stockDate: String,
    ): StockWithDetails?
}
