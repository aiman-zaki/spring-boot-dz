package com.github.aimanzaki.springbootdz.models

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.Table

@Entity
@Table(name = "stock_histories")
class StockHistory(

    @Column(name = "quantity_in")
    val quantityIn: Int,
    @Column(name = "quantity_balance")
    val quantityBalance: Int,

    @ManyToOne(targetEntity = Stock::class, fetch = FetchType.LAZY)
    @JoinColumn(name = "stock_id", referencedColumnName = "id")
    val stock: Stock,

    @ManyToOne
    @JoinColumn(name = "product_id")
    val product: Product,

) {

    @Id
    @GeneratedValue
    val id: Long = 0
}
