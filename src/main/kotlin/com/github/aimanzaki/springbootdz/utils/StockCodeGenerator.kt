package com.github.aimanzaki.springbootdz.utils

import org.apache.commons.lang3.RandomStringUtils
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class StockCodeGenerator {
    companion object {
        private val DATE_FORMAT = DateTimeFormatter.ofPattern("yyMMddHHmmss")

        fun generate(branchCode: String): String {
            return branchCode.plus(LocalDateTime.now().format(DATE_FORMAT).plus(RandomStringUtils.randomAlphabetic(4).uppercase()))
        }
    }
}
