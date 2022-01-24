package com.github.aimanzaki.springbootdz.models

import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.GenericGenerator
import org.hibernate.annotations.UpdateTimestamp
import java.time.OffsetDateTime
import java.util.UUID
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.OneToMany
import javax.persistence.Table

@Entity
@Table(name = "suppliers")
class Supplier(

    var name: String? = null,

) {
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "uuid2")
    @Id
    val id: UUID = UUID.randomUUID()

    @field:CreationTimestamp
    @Column(name = "created_at")
    lateinit var createdAt: OffsetDateTime

    @field:UpdateTimestamp
    @Column(name = "updated_at")
    lateinit var updatedAt: OffsetDateTime

    @OneToMany(mappedBy = "supplier")
    lateinit var product: MutableList<Product>

    @Column(name = "is_active")
    var isActive: Boolean = true

    @Column(name = "code", nullable = true, length = 20)
    var code: String? = ""
}
