package com.github.aimanzaki.springbootdz.controllers

import com.github.aimanzaki.springbootdz.dto.PageDto
import com.github.aimanzaki.springbootdz.dto.StockDto
import com.github.aimanzaki.springbootdz.services.StockService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.format.annotation.DateTimeFormat
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDate

@RestController
@RequestMapping("/api/stocks")
class StocksController(
    val stockService: StockService,
) {
    companion object {
        val log: Logger = LoggerFactory.getLogger(StocksController::class.java)

        private const val DEFAULT_SORT_BY = "stockDate"
        private const val DEFAULT_SORT_DIRECTION = "DESC"
    }

    @GetMapping
    fun getStocks(
        @RequestParam(required = true) pageNumber: Int,
        @RequestParam(required = true) pageSize: Int,
        @RequestParam(required = false, defaultValue = "") stockId: String?,
        @RequestParam(required = false, defaultValue = "") branchId: String?,
        @RequestParam(required = true, defaultValue = DEFAULT_SORT_BY) sortBy: String,
        @RequestParam(required = true, defaultValue = DEFAULT_SORT_DIRECTION) sortDirection: String,
        @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) stockDate: LocalDate?,
    ): ResponseEntity<PageDto<StockDto>> {

        return ResponseEntity.ok(
            stockService.fetchStocksPageable(
                stockDate = stockDate,
                pageable = PageRequest.of(pageNumber, pageSize, Sort.Direction.fromString(sortDirection), sortBy),
                branchId = branchId,
                stockId = stockId,
            )
        )
    }
}
