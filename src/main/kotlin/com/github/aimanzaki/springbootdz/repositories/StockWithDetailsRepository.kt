package com.github.aimanzaki.springbootdz.repositories

import com.github.aimanzaki.springbootdz.models.StockWithDetails
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface StockWithDetailsRepository : JpaRepository<StockWithDetails, UUID> {
    fun findByStockId(stockId: UUID): List<StockWithDetails>
    fun findByIdProductCode(productCode: String): List<StockWithDetails>
}
