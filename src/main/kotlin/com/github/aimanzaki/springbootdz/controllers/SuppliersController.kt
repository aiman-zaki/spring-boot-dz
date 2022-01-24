package com.github.aimanzaki.springbootdz.controllers

import com.github.aimanzaki.springbootdz.dto.SupplierDto
import com.github.aimanzaki.springbootdz.services.SupplierService
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

@RestController
@RequestMapping("/api/suppliers")
class SuppliersController(private val supplierService: SupplierService) {

    @PostMapping
    fun createSupplier(@RequestBody supplierDto: SupplierDto) {
        supplierService.createSupplier(supplierDto)
    }

    @PutMapping("/{supplierId}")
    fun updateSupplier(@PathVariable supplierId: UUID, @RequestBody supplierDto: SupplierDto) {
        supplierService.updateSupplier(supplierId, supplierDto)
    }
}
