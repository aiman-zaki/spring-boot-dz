package com.github.aimanzaki.springbootdz.services

import com.github.aimanzaki.springbootdz.api.response.PageDto
import com.github.aimanzaki.springbootdz.api.response.StockDto
import com.github.aimanzaki.springbootdz.datamapping.toDto
import com.github.aimanzaki.springbootdz.datamapping.toPageDto
import com.github.aimanzaki.springbootdz.repositories.StockRepository
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import java.time.LocalDate

@Service
class StocksService(
    var stockRepository: StockRepository,
    var stocksWithDetailsService: StocksWithDetailsService,
) {

    fun fetchStocksPageable(
        stockId: String?,
        branchId: String?,
        stockDate: LocalDate?,
        pageable: Pageable,
    ): PageDto<StockDto> {
        val data = stockRepository.findAllWithFilterAndPageable(
            stockId,
            branchId, pageable
        )
        val content = data.content.map { stock -> stock.toDto() }
        return data.toPageDto(content = content)
    }
}
