package com.github.aimanzaki.springbootdz.models

import java.time.OffsetDateTime
import java.util.UUID
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.Table

@Entity(name = "product_price")
@Table(name = "product_prices")
class ProductPrice(

    @Id
    @Column(name = "product_id")
    val productId: UUID,

    @Column(name = "price_sell_in_rm")
    val priceSellInRM: Double,

    @Column(name = "price_cost_in_rm")
    val priceCostInRM: Double,

    @Column(name = "is_active")
    val isActive: Boolean,

    @Column(name = "date_active")
    val dateActive: OffsetDateTime,

) {

    @Column(name = "date_end", nullable = true)
    val dateEnd: OffsetDateTime? = null

    @ManyToOne
    @JoinColumn(name = "product_id", insertable = false, updatable = false)
    lateinit var product: Product
}
