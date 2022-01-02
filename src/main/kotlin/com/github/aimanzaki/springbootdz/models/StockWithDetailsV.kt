package com.github.aimanzaki.springbootdz.models

import org.hibernate.annotations.Immutable
import java.io.Serializable
import javax.persistence.Column
import javax.persistence.Embeddable
import javax.persistence.EmbeddedId
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.Table

@Embeddable
class StockWithDetailsId(
    @Column(name = "stock_id")
    val stockId: String,

    @Column(name = "product_code")
    val productCode: String,

) : Serializable

/*
*   View
*   create or replace view stocks_with_details as (select s.id , p.code,
    sh2.yesterday_quantity_balance,
    sh.quantity_in ,
    (pp.price_cost_in_rm * sh.quantity_in) as modal,
    abs(sh.quantity_balance) as quantity_balance,
    greatest((sh2.yesterday_quantity_balance + sh.quantity_in - sh.quantity_balance),0)  as quantity_sale,
    greatest((sh2.yesterday_quantity_balance + sh.quantity_in - sh.quantity_balance),0) * pp.price_sell_in_rm  as sale,
    s.created_at ::date as stock_date
    from stocks s
    left join stock_histories sh on sh.stock_id = s.id
    left join products p on  sh.product_id = p.id
    left join product_prices pp on p.code = pp.product_code
    left join lateral (select sh2.quantity_balance as yesterday_quantity_balance from stock_histories sh2 join stocks s2 on s2.id  = sh2.stock_id where s2.created_at ::date = s.created_at ::date -  INTERVAL '1 DAY' and p.id = sh2.product_id) sh2 on true
    where pp.is_active = true
    group by s.id , p.code , p.id , sh.quantity_in,sh.quantity_balance ,  p.code, pp.price_cost_in_rm, pp.price_sell_in_rm , sh2.yesterday_quantity_balance, s.created_at
    order by stock_date, p.id)

* * */
@Entity
@Immutable
@Table(name = "stocks_with_details")
class StockWithDetails(

    @EmbeddedId
    val id: StockWithDetailsId,

    @Column(name = "yesterday_quantity_balance")
    val yesterdayQuantityBalance: Int?,

    @Column(name = "quantity_in")
    val quantityIn: Int?,

    @Column(name = "modal")
    val modalPrice: Double,

    @Column(name = "quantity_balance")
    val quantityBalance: Int?,

    @Column(name = "sale")
    val sale: Double?,

    @ManyToOne(targetEntity = Stock::class, fetch = FetchType.LAZY)
    @JoinColumn(name = "stock_code", insertable = false, updatable = false)
    val stock: Stock

)
