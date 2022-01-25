package com.github.aimanzaki.springbootdz.dto

import java.util.UUID

data class ProductDto(
    val id: UUID?,
    val code: String?,
    val name: String?,
    val supplierId: String?,
    val isActive: Boolean,
)
