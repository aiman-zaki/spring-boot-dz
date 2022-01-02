package com.github.aimanzaki.springbootdz.enums

data class GetStockParam(
    val branchId: Long?,
    val dayMonthYear: String,
    val pageSize: Int,
    val page: Int
)
