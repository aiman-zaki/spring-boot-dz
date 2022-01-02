package com.github.aimanzaki.springbootdz.utils

import com.github.aimanzaki.springbootdz.csv.StockCsv
import com.github.aimanzaki.springbootdz.csv.StockInAndBalance
import com.opencsv.CSVReader
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.nio.file.Files
import java.nio.file.Paths
import java.time.LocalDate

class ReadStockFromCsv {

    companion object {
        private val log: Logger = LoggerFactory.getLogger(ReadStockFromCsv::class.java)

        fun fromRowToStockCsv(row: List<String>): StockCsv {
            val stockCsv = StockCsv(product = "", stockInAndBalance = mutableListOf())
            var isYesterday = false
            var isToday = false
            var stockInAndBalance = StockInAndBalance(0, 0, LocalDate.now())
            var dayIndex = 1
            while (dayIndex != row.size) {
                if (isYesterday and isToday) {
                    log.info(" :: Retrieving data for day" + stockCsv.product)
                    stockCsv.stockInAndBalance.add(stockInAndBalance)
                    stockInAndBalance = StockInAndBalance(0, 0, LocalDate.now())
                    isYesterday = false
                    isToday = false
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

        fun fromMultipart(row: String): StockCsv {
            val stocksRow = row.split(",")
            return this.fromRowToStockCsv(stocksRow)
        }

        fun lineByLine(): List<StockCsv> {
            log.info(":: Reading from .csv :: ")
            val reader = Files.newBufferedReader(Paths.get("src/main/resources/csv/july-2021.csv"))
            val csvReader = CSVReader(reader)
            val stocksCsv: MutableList<StockCsv> = arrayListOf()
            var nextRecord: Array<String>? = csvReader.readNext()
            while (!nextRecord.isNullOrEmpty()) {
                log.info(" :: Retrieving ... :: ")
                val stockCsv = StockCsv(product = "", stockInAndBalance = mutableListOf())
                stockCsv.product = nextRecord[0]
                var isYesterday = false
                var isToday = false
                var stockInAndBalance = StockInAndBalance(0, 0, LocalDate.now())
                var dayIndex = 1
                while (dayIndex != nextRecord.size) {
                    if (isYesterday and isToday) {
                        log.info(" :: Retrieving data for day" + stockCsv.product)
                        stockCsv.stockInAndBalance.add(stockInAndBalance)
                        stockInAndBalance = StockInAndBalance(0, 0, LocalDate.now())
                        isYesterday = false
                        isToday = false
                    } else if (!isYesterday) {
                        stockInAndBalance.stockIn = nextRecord[dayIndex].toInt()
                        isYesterday = true
                        dayIndex += 1
                    } else if (!isToday) {
                        stockInAndBalance.stockBalance = nextRecord[dayIndex].toInt()
                        isToday = true
                        dayIndex += 1
                    }
                }
                log.info(" :: ${stockCsv.product} ${stockCsv.stockInAndBalance}")
                stocksCsv.add(stockCsv)
                log.info(" :: Retrieved ... :: " + stockCsv.product)
                nextRecord = csvReader.readNext()
            }
            return stocksCsv
        }
    }
}
