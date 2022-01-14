package com.github.aimanzaki.springbootdz.models

import java.io.Serializable
import java.util.UUID
import javax.persistence.Column
import javax.persistence.Embeddable
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.Id
import javax.persistence.IdClass
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.Table

@Embeddable
data class StockHistoryId(
    val stockId: UUID,
    val productId: UUID,
) : Serializable

@Entity
@Table(name = "stock_histories")
@IdClass(StockHistoryId::class)
class StockHistory(

    @Id
    @Column(name = "stock_id")
    val stockId: UUID,

    @Id
    @Column(name = "product_id")
    val productId: UUID,

    @Column(name = "quantity_in")
    val quantityIn: Int,
    @Column(name = "quantity_balance")
    val quantityBalance: Int,

) {

    @ManyToOne(targetEntity = Stock::class, fetch = FetchType.EAGER)
    @JoinColumn(name = "stock_id", insertable = false, updatable = false)
    lateinit var stock: Stock

    @ManyToOne(targetEntity = Product::class, fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id", insertable = false, updatable = false)
    lateinit var product: Product
}
