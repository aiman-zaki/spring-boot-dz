package com.github.aimanzaki.springbootdz.services

import com.github.aimanzaki.springbootdz.api.response.StockWithDetailsDto
import com.github.aimanzaki.springbootdz.models.StockWithDetails
import com.github.aimanzaki.springbootdz.repositories.StocksWithDetailsRepository
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class StocksWithDetailsService(var stocksWithDetailsRepository: StocksWithDetailsRepository) {

    fun fetchStockDetailsByProductCode(productCode: String): List<StockWithDetailsDto> {
        return stocksWithDetailsRepository.findByIdProductCode(productCode).map { stockWithDetails: StockWithDetails ->
            StockWithDetailsDto(
                productCode = null,
                yesterdayQuantityBalance = stockWithDetails.yesterdayQuantityBalance,
                quantityIn = stockWithDetails.quantityIn,
                modalPrice = stockWithDetails.modalPrice,
                quantityBalance = stockWithDetails.quantityBalance,
                sale = stockWithDetails.sale,
                stockDate = stockWithDetails.stock.createdAt,
            )
        }
    }

    fun fetchStockDetailsByStockId(stockId: UUID): List<StockWithDetailsDto> {
        val stock = stocksWithDetailsRepository.findByStockId(stockId).map { stockWithDetails: StockWithDetails ->
            StockWithDetailsDto(
                productCode = stockWithDetails.id.productCode,
                yesterdayQuantityBalance = stockWithDetails.yesterdayQuantityBalance,
                quantityIn = stockWithDetails.quantityIn,
                modalPrice = stockWithDetails.modalPrice,
                quantityBalance = stockWithDetails.quantityBalance,
                sale = stockWithDetails.sale,
                null
            )
        }
        return stock
    }
}
