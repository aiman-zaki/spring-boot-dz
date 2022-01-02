package com.github.aimanzaki.springbootdz.models

import java.time.OffsetDateTime
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.OneToOne
import javax.persistence.Table

@Entity(name = "product_price")
@Table(name = "product_prices")
class ProductPrice(

    @Column(name = "price_sell_in_rm")
    val priceSellInRM: Double,

    @Column(name = "price_cost_in_rm")
    val priceCostInRM: Double,

    @Column(name = "is_active")
    val isActive: Boolean,

    @Column(name = "date_active")
    val dateActive: OffsetDateTime,

) {

    @Id
    @GeneratedValue
    val id: Long = 0

    @Column(name = "date_end", nullable = true)
    val dateEnd: OffsetDateTime? = null

    @OneToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    lateinit var product: Product
}
