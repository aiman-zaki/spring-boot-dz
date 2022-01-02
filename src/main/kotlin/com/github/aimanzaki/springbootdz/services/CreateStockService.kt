package com.github.aimanzaki.springbootdz.services

import com.github.aimanzaki.springbootdz.csv.StockCsv
import com.github.aimanzaki.springbootdz.enums.Authorities
import com.github.aimanzaki.springbootdz.models.Product
import com.github.aimanzaki.springbootdz.models.ProductPrice
import com.github.aimanzaki.springbootdz.models.Stock
import com.github.aimanzaki.springbootdz.models.StockHistory
import com.github.aimanzaki.springbootdz.models.Supplier
import com.github.aimanzaki.springbootdz.models.User
import com.github.aimanzaki.springbootdz.repositories.ProductRepository
import com.github.aimanzaki.springbootdz.repositories.StocksRepository
import com.github.aimanzaki.springbootdz.repositories.SuppliersRepository
import com.github.aimanzaki.springbootdz.repositories.UsersRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDate
import java.time.OffsetDateTime
import java.util.UUID

@Service
@Transactional
class CreateStockService(
    val usersRepository: UsersRepository,
    val productRepository: ProductRepository,
    val stockRepository: StocksRepository,
    val suppliersRepository: SuppliersRepository,
) {

    fun createStockFromCsv(userId: UUID, stocksCsv: List<StockCsv>) {

        var user = User(
            email = "test@yopmail.com",
            password = "123123",
            authorities = listOf<Authorities>().plus(Authorities.ADMIN),
            name = "test"
        )

        var supplier = Supplier(
            name = "Wak Jono"
        )

        supplier = suppliersRepository.save(supplier)
        user = usersRepository.save(user)

        val dayMaxLength = stocksCsv[0].stockInAndBalance.size // Column size is the same for all
        var stockDate = LocalDate.now()

        for (dayIndex in 0 until dayMaxLength) {
            val stock = Stock(user = user)
            // Need to manually assigned
            stock.createdAt = OffsetDateTime.now()

            val stockHistories: MutableList<StockHistory> = mutableListOf()
            for (stockCsv in stocksCsv) {

                var product = productRepository.findByCode(stockCsv.product).orElse(
                    Product(
                        code = stockCsv.product,
                        name = stockCsv.product,
                        supplier = supplier,
                        productPrice = ProductPrice(
                            priceSellInRM = 0.00,
                            priceCostInRM = 0.00,
                            isActive = true,
                            dateActive = OffsetDateTime.now()
                        )
                    )
                )

                product.productPrice.product = product
                product = productRepository.save(product)

                val stockHistory = StockHistory(
                    stock = stock,
                    product = product,
                    quantityBalance = stockCsv.stockInAndBalance[dayIndex].stockIn,
                    quantityIn = stockCsv.stockInAndBalance[dayIndex].stockBalance
                )
                stockDate = stockCsv.stockInAndBalance[dayIndex].stockDate // FIXME : better impl than this T_T
                stockHistories.add(stockHistory)
            }

            stock.stockHistories = stockHistories
            stock.stockDate = stockDate
            stockRepository.save(stock)
        }
    }
}
