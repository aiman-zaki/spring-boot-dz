package com.github.aimanzaki.springbootdz.controllers

import com.github.aimanzaki.springbootdz.dto.ProductDto
import com.github.aimanzaki.springbootdz.services.ProductService
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/products")
class ProductsController(private val productService: ProductService) {

    companion object {
        private const val DEFAULT_SORT_BY = "createdAt"
        private const val DEFAULT_SORT_DIRECTION = "DESC"
    }

    @GetMapping
    fun getProducts(
        @RequestParam(name = "id", defaultValue = "") id: String?,
        @RequestParam(name = "name", defaultValue = "") name: String?,
        @RequestParam(name = "code", defaultValue = "") code: String?,
        @RequestParam(name = "isActive") isActive: Boolean,
        @RequestParam(required = true, defaultValue = DEFAULT_SORT_BY) sortBy: String,
        @RequestParam(required = true, defaultValue = DEFAULT_SORT_DIRECTION) sortDirection: String,
        @RequestParam(required = true) pageNumber: Int,
        @RequestParam(required = true) pageSize: Int,

    ) {
        this.productService.getProductsWithFilter(
            id = id,
            name = name,
            code = code,
            isActive = isActive,
            pageable = PageRequest.of(pageNumber, pageSize, Sort.Direction.fromString(sortDirection), sortBy),
        )
    }

    @PostMapping
    fun createProduct(@RequestBody productDto: ProductDto): ResponseEntity<ProductDto> {
        return ResponseEntity.ok(this.productService.createProduct(productDto))
    }

    @PutMapping
    fun updateProduct() {
    }
}
