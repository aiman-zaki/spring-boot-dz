package com.github.aimanzaki.springbootdz.services

import com.github.aimanzaki.springbootdz.csv.StockCsv
import com.github.aimanzaki.springbootdz.csv.StockInAndBalance
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Service
class ReadStockFromExternalService {

    // FIXME : its too ugly
    private val log: Logger = LoggerFactory.getLogger(ReadStockFromExternalService::class.java)
    fun fromRowToStockCsv(row: List<String>, date: List<String>): StockCsv {
        val stockCsv = StockCsv(product = row[0], stockInAndBalance = mutableListOf())
        var isYesterday = false
        var isToday = false
        var stockInAndBalance = StockInAndBalance(0, 0, LocalDate.now())
        var dayIndex = 1
        var dateIndex = 0

        while (dayIndex != (row.size + 1)) {
            if (isYesterday and isToday) {
                log.info(" :: Retrieving data for day " + stockCsv.product)
                stockInAndBalance.stockDate =
                    LocalDate.parse(date[dateIndex], DateTimeFormatter.ofPattern("dd/MM/yyyy"))
                stockCsv.stockInAndBalance.add(stockInAndBalance)
                stockInAndBalance = StockInAndBalance(0, 0, LocalDate.now())
                isYesterday = false
                isToday = false
                dateIndex += 1
                if (dayIndex == row.size) break
            } else if (!isYesterday) {
                stockInAndBalance.stockIn = row[dayIndex].toInt()
                isYesterday = true
                dayIndex += 1
            } else if (!isToday) {
                stockInAndBalance.stockBalance = row[dayIndex].toInt()
                isToday = true
                dayIndex += 1
            }
        }
        log.info(" :: Retrieved ... :: " + stockCsv.product)
        return stockCsv
    }

    fun fromMultipart(row: String, date: List<String>): StockCsv {
        val stocksRow = row.split(",")
        return this.fromRowToStockCsv(stocksRow, date)
    }
}
