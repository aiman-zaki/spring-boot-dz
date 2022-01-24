package com.github.aimanzaki.springbootdz.services

import com.github.aimanzaki.springbootdz.datamapping.toDto
import com.github.aimanzaki.springbootdz.datamapping.toPageDto
import com.github.aimanzaki.springbootdz.dto.PageDto
import com.github.aimanzaki.springbootdz.dto.StockDto
import com.github.aimanzaki.springbootdz.repositories.StockRepository
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Service
class StockService(
    var stockRepository: StockRepository,
    var stocksWithDetailsService: StocksWithDetailsService,
) {

    fun fetchStocksPageable(
        stockId: String?,
        branchId: String?,
        stockDate: LocalDate?,
        pageable: Pageable,
    ): PageDto<StockDto> {

        var stockDateFormatted = stockDate?.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))

        if (stockDateFormatted.isNullOrBlank()) stockDateFormatted = ""

        val data = stockRepository.findAllWithFilterAndPageable(
            stockId,
            branchId,
            stockDateFormatted,
            pageable
        )
        val content = data.content.map { stock -> stock.toDto() }
        return data.toPageDto(content = content)
    }
}
