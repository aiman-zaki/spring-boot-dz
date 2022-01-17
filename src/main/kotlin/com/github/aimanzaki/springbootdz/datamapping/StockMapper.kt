package com.github.aimanzaki.springbootdz.datamapping

import com.github.aimanzaki.springbootdz.api.response.StockDto
import com.github.aimanzaki.springbootdz.api.response.StockWithDetailsDto
import com.github.aimanzaki.springbootdz.models.Stock
import com.github.aimanzaki.springbootdz.models.StockWithDetails

fun StockWithDetails.toDto() = StockWithDetailsDto(
    productCode = id.productCode,
    yesterdayQuantityBalance = yesterdayQuantityBalance,
    quantityIn = quantityIn,
    quantityBalance = quantityBalance,
    totalCostInRM = totalCostInRM,
    quantitySoldToday = quantitySoldToday,
    totalSaleInRM = totalSaleInRM,
    stockDate = stockDate
)

fun Stock.toDto() = StockDto(
    id = id,
    stockDate = stockDate,
    branchId = branchId,
    stockWithDetails = stockWithDetails.map { stockWithDetails -> stockWithDetails.toDto() }
)
