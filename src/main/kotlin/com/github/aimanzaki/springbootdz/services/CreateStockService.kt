package com.github.aimanzaki.springbootdz.services

import com.github.aimanzaki.springbootdz.csv.StockCsv
import com.github.aimanzaki.springbootdz.enums.StockSource
import com.github.aimanzaki.springbootdz.models.Product
import com.github.aimanzaki.springbootdz.models.ProductPrice
import com.github.aimanzaki.springbootdz.models.Stock
import com.github.aimanzaki.springbootdz.models.StockHistory
import com.github.aimanzaki.springbootdz.repositories.BranchRepository
import com.github.aimanzaki.springbootdz.repositories.ProductRepository
import com.github.aimanzaki.springbootdz.repositories.StockHistoryRepository
import com.github.aimanzaki.springbootdz.repositories.StockRepository
import com.github.aimanzaki.springbootdz.repositories.StockWithDetailsRepository
import com.github.aimanzaki.springbootdz.repositories.SuppliersRepository
import com.github.aimanzaki.springbootdz.repositories.UserRepository
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.server.ResponseStatusException
import java.time.LocalDate
import java.time.OffsetDateTime
import java.util.UUID

@Service
@Transactional
class CreateStockService(
    val userRepository: UserRepository,
    val productRepository: ProductRepository,
    val stockRepository: StockRepository,
    val suppliersRepository: SuppliersRepository,
    val branchRepository: BranchRepository,
    val stockHistoryRepository: StockHistoryRepository,
    val stockWithDetailsRepository: StockWithDetailsRepository,
) {

    companion object {
        val log: Logger = LoggerFactory.getLogger(CreateStockService::class.java)
    }

    fun createStockFromCsv(
        userId: UUID,
        supplierId: UUID,
        branchId: UUID,
        stocksCsv: List<StockCsv>,
        date: String,
    ) {

        // Ignore if stock with month + branchId already exist in db
        val existStock = stockRepository.findByStockDateAndBranchId(date, branchId)
        if (existStock != null) {
            throw ResponseStatusException(HttpStatus.CONFLICT, "Stock for this particular month already exist")
        }

        val dayMaxLength = stocksCsv[0].stockInAndBalance.size // Column size is the same for all
        var stockDate = LocalDate.now()

        for (dayIndex in 0 until dayMaxLength) {
            var stock = Stock(userId = userId, branchId = branchId, source = StockSource.SHEET)

            // Save the stock for the id
            stock = stockRepository.save(stock)

            val stockHistories: MutableList<StockHistory> = mutableListOf()
            for (stockCsv in stocksCsv) {
                // If product is not exist, create new product
                var product = productRepository.findByCode(stockCsv.product).orElse(
                    Product(
                        code = stockCsv.product,
                        name = stockCsv.product,
                        supplierId = supplierId,

                    )
                )

                product = productRepository.save(product)
                if (product.productsPrice.isEmpty()) {
                    product.productsPrice = mutableListOf(
                        ProductPrice(
                            productId = product.id,
                            priceSellInRM = 0.00,
                            priceCostInRM = 0.00,
                            true,
                            OffsetDateTime.now()
                        )
                    )
                }

                productRepository.save(product)

                val stockHistory = StockHistory(
                    // product = product,
                    quantityBalance = stockCsv.stockInAndBalance[dayIndex].stockBalance,
                    quantityIn = stockCsv.stockInAndBalance[dayIndex].stockIn,
                    stockId = stock.id,
                    productId = product.id
                )
                stockDate = stockCsv.stockInAndBalance[dayIndex].stockDate // FIXME : better impl than this T_T

                stockHistories.add(stockHistory)
            }
            stock.stockDate = stockDate
            stockRepository.save(stock)
            stockHistoryRepository.saveAll(stockHistories)
        }
    }
}
