package com.github.aimanzaki.springbootdz.repositories

import com.github.aimanzaki.springbootdz.models.ProductPrice
import org.springframework.data.jpa.repository.JpaRepository

interface ProductPriceRepository : JpaRepository<ProductPrice, Long>
