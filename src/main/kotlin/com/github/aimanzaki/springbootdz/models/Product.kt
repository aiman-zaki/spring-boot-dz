package com.github.aimanzaki.springbootdz.models

import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.GenericGenerator
import org.hibernate.annotations.UpdateTimestamp
import java.time.OffsetDateTime
import java.util.UUID
import javax.persistence.CascadeType
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.OneToMany
import javax.persistence.Table

@Entity
@Table(name = "products")
class Product(

    @Column(unique = true, length = 50, nullable = false)
    val code: String?,

    val name: String?,

    @Column(name = "supplier_id")
    val supplierId: UUID?,

) {
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "uuid2")
    @Id
    var id: UUID = UUID.randomUUID()

    @field:CreationTimestamp
    @Column(name = "created_at")
    lateinit var createdAt: OffsetDateTime

    @field:UpdateTimestamp
    @Column(name = "updated_at")
    lateinit var updatedAt: OffsetDateTime

    @OneToMany(mappedBy = "product", cascade = [CascadeType.ALL])
    lateinit var stockWithHistories: MutableList<StockHistory>

    @ManyToOne(cascade = [CascadeType.ALL], fetch = FetchType.EAGER)
    @JoinColumn(name = "supplier_id", insertable = false, updatable = false)
    lateinit var supplier: Supplier

    @OneToMany(mappedBy = "product", cascade = [CascadeType.ALL])
    // @Where(clause = "is_active=true")
    var productsPrice: MutableList<ProductPrice> = mutableListOf()

    @Column(name = "is_active")
    val isActive: Boolean = true
}
