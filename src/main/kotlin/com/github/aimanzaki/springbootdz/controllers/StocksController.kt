package com.github.aimanzaki.springbootdz.controllers

import com.github.aimanzaki.springbootdz.api.response.StockDto
import com.github.aimanzaki.springbootdz.api.response.StockWithDetailsDto
import com.github.aimanzaki.springbootdz.enums.GetStockParam
import com.github.aimanzaki.springbootdz.services.StocksService
import com.github.aimanzaki.springbootdz.services.StocksWithDetailsService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.data.domain.Page
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/stocks")
class StocksController(
    val stocksService: StocksService,
    val stocksWithDetailsService: StocksWithDetailsService
) {
    companion object {
        val log: Logger = LoggerFactory.getLogger(StocksController::class.java)
    }
    @GetMapping("/product/{productCode}")
    fun getStocksByProduct(@PathVariable(name = "productCode", required = true) productCode: String): List<StockWithDetailsDto> {
        return stocksWithDetailsService.fetchStockDetailsByProductCode(productCode)
    }

    @GetMapping
    fun getStocks(
        @RequestParam(required = false) year: String?,
        @RequestParam(required = false) month: String?,
        @RequestParam(required = false) day: String?,
        @RequestParam(required = false) branchId: Long?,
        @RequestParam(required = true) pageSize: Int,
        @RequestParam(required = true) page: Int,
    ): Page<StockDto> {

        log.info("::GetStocks::")
        var stocks: Page<StockDto> = Page.empty()
        if (day != null && month != null && year != null) {
            val date = "$day/$month/$year"
            stocks = stocksService.fetchStockByDate(GetStockParam(branchId, date, pageSize, page))
        }
        if (month != null && year != null) {
            val monthYear = "$month/$year"
            stocks = stocksService.fetchStocksByYearAndMonth(GetStockParam(branchId, monthYear, pageSize, page))
        }
        if (day == null && month == null && year != null) {
            stocks = stocksService.fetchStocks(GetStockParam(branchId, "", pageSize, page))
        }
        return stocks
    }
}
