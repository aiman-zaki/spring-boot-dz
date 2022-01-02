package com.github.aimanzaki.springbootdz.models

import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.io.Serializable
import java.time.OffsetDateTime
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.IdClass
import javax.persistence.Table

data class DashboardId(
    val userName: String,
    val monthYear: String,
    val productType: String,

) : Serializable

@Entity
@Table(name = "okb_dashboard")
@IdClass(DashboardId::class)
class OkbDashboard {

    @Id
    @Column(name = "month_year")
    val monthYear: String = ""

    @Column(name = "total_sales")
    val totalSales: Float = 0.0f

    @Column(name = "number_of_clicks")
    val numberOfClicks: Int = 0

    @Column(name = "total_order_submitted")
    val totalOrderSubmitted: Int = 0

    @Column(name = "total_order_completed")
    val totalOrderCompleted: Int = 0

    @Column(name = "total_order_pending")
    val totalOrderPending: Int = 0

    @Column(name = "total_earn")
    val totalEarn: Float = 0.0f

    @Column(name = "total_commission_paid")
    val totalCommissionPaid: Float = 0.0f

    @Id
    @Column(name = "product_type")
    val productType: String = ""

    @Id
    @Column(name = "username", length = 50)
    val userName: String = ""

    @Column(name = "carryfwd_tier")
    val carryForwardTier: String = ""

    @Column(name = "current_tier")
    val currentTier: String = ""

    @field:CreationTimestamp
    @Column(name = "created_date")
    lateinit var createdDate: OffsetDateTime

    @field:UpdateTimestamp
    @Column(name = "updated_date")
    lateinit var updatedDate: OffsetDateTime
}
