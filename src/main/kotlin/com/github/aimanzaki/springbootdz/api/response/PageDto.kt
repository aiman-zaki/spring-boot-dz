package com.github.aimanzaki.springbootdz.api.response

data class PageDto<U>(
    val isFirstPage: Boolean,
    val isLastPage: Boolean,
    val totalItems: Long,
    val totalPages: Int,
    val pageSize: Int,
    val pageNumber: Int,
    val content: List<U>,
)
