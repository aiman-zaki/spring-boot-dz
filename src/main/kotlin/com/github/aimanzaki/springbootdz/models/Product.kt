package com.github.aimanzaki.springbootdz.models

import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.GenericGenerator
import org.hibernate.annotations.UpdateTimestamp
import org.hibernate.annotations.Where
import java.time.OffsetDateTime
import java.util.UUID
import javax.persistence.CascadeType
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.OneToMany
import javax.persistence.OneToOne
import javax.persistence.Table

@Entity(name = "product")
@Table(name = "products")
class Product(

    @Column(unique = true, length = 50)
    val code: String,

    val name: String,

    @ManyToOne(cascade = [CascadeType.ALL])
    @JoinColumn(name = "supplier_id")
    val supplier: Supplier,

    @OneToOne(mappedBy = "product", cascade = [CascadeType.ALL])
    @Where(clause = "is_active=true")
    val productPrice: ProductPrice,

) {
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "uuid2")
    @Id
    var id: UUID = UUID.randomUUID()

    @OneToMany(mappedBy = "product")
    val stockHistories: List<StockHistory> = listOf()

    @field:CreationTimestamp
    @Column(name = "created_at")
    lateinit var createdAt: OffsetDateTime

    @field:UpdateTimestamp
    @Column(name = "updated_at")
    lateinit var updatedAt: OffsetDateTime
}
