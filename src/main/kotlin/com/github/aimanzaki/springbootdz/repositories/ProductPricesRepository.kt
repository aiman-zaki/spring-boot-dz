package com.github.aimanzaki.springbootdz.repositories

import com.github.aimanzaki.springbootdz.models.ProductPrice
import org.springframework.data.jpa.repository.JpaRepository

interface ProductPricesRepository : JpaRepository<ProductPrice, Long>
