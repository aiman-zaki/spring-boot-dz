package com.github.aimanzaki.springbootdz.controllers

import com.github.aimanzaki.springbootdz.utils.ReadStockFromCsv
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/stocks/csv")
class StockCsvController {

    @PostMapping
    @PreAuthorize("hasAuthority('USER')")
    fun uploadStockFromCSV() {
        ReadStockFromCsv.lineByLine()
    }
}
