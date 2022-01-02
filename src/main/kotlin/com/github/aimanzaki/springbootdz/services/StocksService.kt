package com.github.aimanzaki.springbootdz.services

import com.github.aimanzaki.springbootdz.api.response.StockDto
import com.github.aimanzaki.springbootdz.enums.GetStockParam
import com.github.aimanzaki.springbootdz.models.Stock
import com.github.aimanzaki.springbootdz.repositories.StocksRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import java.time.OffsetDateTime

@Service
class StocksService(
    var stocksRepository: StocksRepository,
    var stocksWithDetailsService: StocksWithDetailsService
) {

    fun fetchStocks(getStockParam: GetStockParam): Page<StockDto> {
        val (branchId, dayMonthYear, pageSize, page) = getStockParam
        val pageable: Pageable = PageRequest.of(page, pageSize)

        return stocksRepository.findAll(branchId, pageable).map { stock: Stock ->
            StockDto(
                stock.id,
                stock.branch.id,
                stock.createdAt,
                stocksWithDetailsService.fetchStockDetailsByStockId(stock.id)
            )
        }
    }

    fun fetchStocksByYear(getStockParam: GetStockParam): Page<StockDto> {
        val (branchId, dayMonthYear, pageSize, page) = getStockParam
        val pageable: Pageable = PageRequest.of(page, pageSize)
        return stocksRepository.findStocksByYear(branchId, dayMonthYear, pageable).map { stock: Stock ->
            StockDto(
                stock.id,
                stock.branch.id,
                stock.createdAt,
                stocksWithDetailsService.fetchStockDetailsByStockId(stock.id)
            )
        }
    }

    fun fetchStocksByYearAndMonth(getStockParam: GetStockParam): Page<StockDto> {
        val (branchId, dayMonthYear, pageSize, page) = getStockParam
        val pageable: Pageable = PageRequest.of(page, pageSize)
        return stocksRepository.findStocksByYearAndMonth(branchId, dayMonthYear, pageable).map { stock: Stock ->
            StockDto(
                stock.id,
                stock.branch.id,
                stock.createdAt,
                stocksWithDetailsService.fetchStockDetailsByStockId(stock.id)
            )
        }
    }

    fun fetchStockByDate(getStockParam: GetStockParam): Page<StockDto> {
        val (branchId, dayMonthYear, pageSize, page) = getStockParam
        val pageable: Pageable = PageRequest.of(page, pageSize)
        return stocksRepository.findStocksByDate(branchId, dayMonthYear, pageable).map { stock: Stock ->
            StockDto(
                stock.id,
                stock.branch.id,
                stock.createdAt,
                stocksWithDetailsService.fetchStockDetailsByStockId(stock.id)
            )
        }
    }

    fun fetchYesterdayStock(branchId: Long?, date: OffsetDateTime): StockDto {
        TODO("Not yet implemented")
    }
}
