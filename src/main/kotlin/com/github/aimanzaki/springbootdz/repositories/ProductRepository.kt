package com.github.aimanzaki.springbootdz.repositories

import com.github.aimanzaki.springbootdz.models.Product
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import java.util.Optional
import java.util.UUID

interface ProductRepository : JpaRepository<Product, UUID> {
    fun findByCode(code: String): Optional<Product>

    @Query(
        "SELECT p FROM Product p WHERE CAST(id as org.hibernate.type.UUIDCharType) LIKE CONCAT('%',:id,'%') AND " +
            "name LIKE CONCAT('%',:name,'%') AND code LIKE CONCAT('%',:code,'%') and isActive = :isActive"
    )
    fun findWithFilterAndPageable(
        @Param("id") id: String?,
        @Param("code") code: String?,
        @Param("name") name: String?,
        @Param("isActive") isActive: Boolean,
        pageable: Pageable,

    ): Page<Product>
}
