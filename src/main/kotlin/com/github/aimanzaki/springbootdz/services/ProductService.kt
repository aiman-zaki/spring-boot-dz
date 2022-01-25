package com.github.aimanzaki.springbootdz.services

import com.github.aimanzaki.springbootdz.datamapping.toDto
import com.github.aimanzaki.springbootdz.datamapping.toEntity
import com.github.aimanzaki.springbootdz.datamapping.toPageDto
import com.github.aimanzaki.springbootdz.dto.PageDto
import com.github.aimanzaki.springbootdz.dto.ProductDto
import com.github.aimanzaki.springbootdz.repositories.ProductRepository
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class ProductService(private val productRepository: ProductRepository) {

    fun getProductsWithFilter(
        id: String?,
        code: String?,
        name: String?,
        isActive: Boolean,
        pageable: Pageable,
    ): PageDto<ProductDto> {

        val products = productRepository.findWithFilterAndPageable(
            id = id,
            code = code,
            name = name,
            isActive = isActive, pageable = pageable
        )
        val contents = products.content.map { product -> product.toDto() }
        return products.toPageDto(contents)
    }

    fun createProduct(productDto: ProductDto): ProductDto {
        val product = this.productRepository.save(productDto.toEntity())

        return product.toDto()
    }
}
