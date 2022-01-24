package com.github.aimanzaki.springbootdz.services

import com.github.aimanzaki.springbootdz.datamapping.toEntity
import com.github.aimanzaki.springbootdz.dto.SupplierDto
import com.github.aimanzaki.springbootdz.models.Supplier
import com.github.aimanzaki.springbootdz.repositories.SuppliersRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class SupplierService(private val suppliersRepository: SuppliersRepository) {

    fun createSupplier(supplierDto: SupplierDto) {
        suppliersRepository.save(supplierDto.toEntity(Supplier()))
    }

    fun updateSupplier(id: UUID, supplierDto: SupplierDto) {
        val supplier = suppliersRepository.findByIdOrNull(id)
        if (supplier != null) {
            suppliersRepository.save(supplier)
        }
    }
}
