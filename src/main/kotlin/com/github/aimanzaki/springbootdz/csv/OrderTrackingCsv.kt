package com.github.aimanzaki.springbootdz.csv

import com.opencsv.bean.CsvBindByName

data class OrderTrackingCsv(
    @CsvBindByName(column = "orderId")
    val orderId: String
) : CsvBean()
