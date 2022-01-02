package com.github.aimanzaki.springbootdz.api.response

data class OkbDashboardDto(
    val monthYear: String,
    val productType: String,
    val username: String,
    val numberOfClicks: Long,
)
