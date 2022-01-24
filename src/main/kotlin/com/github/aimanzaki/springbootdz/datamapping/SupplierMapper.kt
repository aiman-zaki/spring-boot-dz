package com.github.aimanzaki.springbootdz.datamapping

import com.github.aimanzaki.springbootdz.dto.SupplierDto
import com.github.aimanzaki.springbootdz.models.Supplier

fun SupplierDto.toEntity(supplier: Supplier): Supplier {
    supplier.code = this.code
    supplier.isActive = this.isActive
    supplier.name = this.name
    return supplier
}
