package com.github.aimanzaki.springbootdz.csv

import java.time.LocalDate

data class StockInAndBalance(
    var stockIn: Int,
    var stockBalance: Int,
    var stockDate: LocalDate
)

data class StockCsv(
    var product: String,
    var stockInAndBalance: MutableList<StockInAndBalance>
)
