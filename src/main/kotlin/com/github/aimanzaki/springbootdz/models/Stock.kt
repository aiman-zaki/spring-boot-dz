package com.github.aimanzaki.springbootdz.models

import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.GenericGenerator
import org.hibernate.annotations.Type
import org.hibernate.annotations.UpdateTimestamp
import java.time.LocalDate
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
import javax.persistence.Table

@Entity
@Table(name = "stocks")
class Stock(

    @ManyToOne
    @JoinColumn(name = "user_id")
    val user: User
) {
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "uuid2")
    @Type(type = "org.hibernate.type.UUIDCharType")
    @Id
    var id: UUID = UUID.randomUUID()

    @OneToMany(mappedBy = "stock", cascade = [CascadeType.ALL])
    lateinit var stockHistories: List<StockHistory>

    @Column(name = "stock_date", nullable = false)
    var stockDate: LocalDate = LocalDate.now()

    @field:CreationTimestamp
    @Column(name = "created_at")
    lateinit var createdAt: OffsetDateTime

    @field:UpdateTimestamp
    @Column(name = "updated_at")
    lateinit var updatedAt: OffsetDateTime

    @ManyToOne
    @JoinColumn(name = "branch_id")
    lateinit var branch: Branch

    @OneToMany(mappedBy = "stock", cascade = [CascadeType.ALL])
    val stockWithDetails: List<StockWithDetails> = listOf()
}
