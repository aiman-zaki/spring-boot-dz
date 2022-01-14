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
@Table(name = "branches")
class Branch(

    @Column(name = "code", length = 10)
    val code: String,

    @Column(name = "name")
    val name: String,

) {
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "uuid2")
    @Id
    val id: UUID = UUID.randomUUID()

    @CreationTimestamp
    @Column(insertable = false, updatable = false, name = "created_at")
    lateinit var createdAt: OffsetDateTime

    @UpdateTimestamp
    @Column(insertable = false, updatable = false, name = "updated_at")
    lateinit var updatedAt: OffsetDateTime

    @OneToMany(mappedBy = "branch")
    lateinit var stocks: List<Stock>
}
