package com.github.aimanzaki.springbootdz.datamapping

import com.github.aimanzaki.springbootdz.api.response.PageDto
import org.springframework.data.domain.Page

fun <T, U> Page<T>.toPageDto(content: List<U>) = PageDto(
    content = content,
    isFirstPage = this.isFirst,
    isLastPage = this.isLast,
    totalItems = this.totalElements,
    totalPages = this.totalPages,
    pageSize = this.numberOfElements,
    pageNumber = this.number,
)
