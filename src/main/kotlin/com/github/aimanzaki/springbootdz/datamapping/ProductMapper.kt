package com.github.aimanzaki.springbootdz.datamapping

import com.github.aimanzaki.springbootdz.dto.ProductDto
import com.github.aimanzaki.springbootdz.models.Product
import java.util.UUID

fun ProductDto.toEntity() = Product(
    name = this.name,
    supplierId = UUID.fromString(this.supplierId),
    code = this.code,
)

fun Product.toDto() = ProductDto(
    id = this.id,
    supplierId = this.supplierId.toString(),
    code = this.code,
    isActive = this.isActive,
    name = this.name,
)
