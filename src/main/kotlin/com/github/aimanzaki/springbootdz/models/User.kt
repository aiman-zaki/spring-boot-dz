package com.github.aimanzaki.springbootdz.models

import com.github.aimanzaki.springbootdz.enums.Authorities
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.GenericGenerator
import org.hibernate.annotations.UpdateTimestamp
import java.time.OffsetDateTime
import java.util.UUID
import javax.persistence.CascadeType
import javax.persistence.Column
import javax.persistence.ElementCollection
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.OneToMany
import javax.persistence.Table

@Entity
@Table(name = "users")
class User(

    @Column(name = "email", nullable = false)
    val email: String,

    @Column(name = "name", nullable = true)
    val name: String,

    @Column(name = "password", nullable = false)
    val password: String,

    @ElementCollection(targetClass = Authorities::class)
    @Enumerated(EnumType.STRING)
    val authorities: Collection<Authorities>,

) {

    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "uuid2")
    @Id
    val id: UUID = UUID.randomUUID()

    @field:CreationTimestamp
    @Column(insertable = false, updatable = false, name = "created_at")
    lateinit var createdAt: OffsetDateTime

    @field:UpdateTimestamp
    @Column(insertable = false, updatable = false, name = "updated_at")
    lateinit var updatedAt: OffsetDateTime

    @OneToMany(mappedBy = "user", cascade = [CascadeType.ALL])
    lateinit var stocks: List<Stock>
}
