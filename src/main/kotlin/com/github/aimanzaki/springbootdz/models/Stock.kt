package com.github.aimanzaki.springbootdz.models

import com.github.aimanzaki.springbootdz.enums.StockSource
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.GenericGenerator
import org.hibernate.annotations.UpdateTimestamp
import java.time.LocalDate
import java.time.OffsetDateTime
import java.util.UUID
import javax.persistence.CascadeType
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.FetchType
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

    @Column(name = "user_id")
    val userId: UUID,

    @Column(name = "branch_id")
    val branchId: UUID,

    @Enumerated(EnumType.STRING)
    @Column(name = "source", nullable = false)
    val source: StockSource = StockSource.MANUAL,

) {
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "uuid2")
    @Id
    var id: UUID = UUID.randomUUID()

    @Column(name = "stock_date", nullable = false)
    var stockDate: LocalDate = LocalDate.now()

    @field:CreationTimestamp
    @Column(name = "created_at")
    lateinit var createdAt: OffsetDateTime

    @field:UpdateTimestamp
    @Column(name = "updated_at")
    lateinit var updatedAt: OffsetDateTime

    @OneToMany(mappedBy = "stock", cascade = [CascadeType.ALL])
    lateinit var stockHistories: MutableList<StockHistory>

    @OneToMany(mappedBy = "stock")
    lateinit var stockWithDetails: MutableList<StockWithDetails>

    @ManyToOne(targetEntity = User::class, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", updatable = false, insertable = false)
    lateinit var user: User

    @ManyToOne(targetEntity = Branch::class, fetch = FetchType.EAGER)
    @JoinColumn(name = "branch_id", updatable = false, insertable = false)
    lateinit var branch: Branch
}
