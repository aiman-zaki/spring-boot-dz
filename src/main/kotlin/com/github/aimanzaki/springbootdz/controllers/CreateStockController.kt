package com.github.aimanzaki.springbootdz.controllers

import com.github.aimanzaki.springbootdz.csv.StockCsv
import com.github.aimanzaki.springbootdz.services.CreateStockService
import com.github.aimanzaki.springbootdz.services.ReadStockFromExternalService
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile
import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.UUID

@RestController
class CreateStockController(
    var createStockService: CreateStockService,
    var readStockFromExternalService: ReadStockFromExternalService,
) {

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping("/api/stocks/create-by-csv")
    fun getStock() {
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PostMapping("/api/stocks/create-by-csv")
    fun createStockByCsv(
        @RequestParam("file") file: MultipartFile,
        @RequestParam("branchId") branchId: UUID,
        @RequestParam("supplierId") supplierId: UUID,
    ): ResponseEntity<String> {

        val stocksCsv: MutableList<StockCsv> = arrayListOf()
        val inputStream = file.inputStream
        val bufferedReader = BufferedReader(InputStreamReader(inputStream))

        val rowDate = bufferedReader.readLine().split(",")
        // FIXME : theres better impl than this
        val preferredDateFormat = """\d{1,2}.\d{1,2}.\d{4}""".toRegex()
        val singleDigitDayRegex = """\d.\d{1,2}.\d{4}""".toRegex()
        val singleDigitMonthRegex = """\d{1,2}.\d{1,2}.\d{4}""".toRegex()

        var date = rowDate.map { date -> preferredDateFormat.find(date)?.value }
        date = date.distinct().filterNotNull()
        date = date.map { d -> if (singleDigitDayRegex.matches(d)) "0$d" else d }
        date = date.map { d -> if (singleDigitMonthRegex.matches(d)) d.replaceRange(3, 3, "0") else d }

        var nextLine = bufferedReader.readLine()
        while (nextLine != null) {
            stocksCsv.add(readStockFromExternalService.fromMultipart(nextLine, date))
            nextLine = bufferedReader.readLine()
        }

        createStockService.createStockFromCsv(
            UUID.fromString(SecurityContextHolder.getContext().authentication.name),
            supplierId,
            branchId,
            stocksCsv,
            date[0]
        )

        return ResponseEntity.ok("Success!")
    }
}
