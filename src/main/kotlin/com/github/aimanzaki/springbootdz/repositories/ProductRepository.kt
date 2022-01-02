package com.github.aimanzaki.springbootdz.repositories

import com.github.aimanzaki.springbootdz.models.Product
import org.springframework.data.jpa.repository.JpaRepository
import java.util.Optional
import java.util.UUID

interface ProductRepository : JpaRepository<Product, UUID> {
    fun findByCode(code: String): Optional<Product>
}
