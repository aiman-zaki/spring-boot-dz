package com.github.aimanzaki.springbootdz.dto

data class PageDto<U>(
    val isFirstPage: Boolean,
    val isLastPage: Boolean,
    val totalItems: Long,
    val totalPages: Int,
    val pageSize: Int,
    val pageNumber: Int,
    val content: List<U>,
)
