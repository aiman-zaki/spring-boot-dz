package com.github.aimanzaki.springbootdz.dto

data class StockHistoryDto(
    var productCode: String,
    var productName: String,
    var quantityIn: Int,
    var quantityBalance: Int,
    var modalPrice: Double,
    var quantitySale: Int?,
    var sale: Double?,
)
