package com.github.aimanzaki.springbootdz.dto

import com.fasterxml.jackson.annotation.JsonProperty
import java.util.UUID

data class BranchDto(
    @JsonProperty("id")
    val id: UUID? = null,

    @JsonProperty("code")
    val code: String?,
    @JsonProperty("name")
    val name: String?,

    @JsonProperty("isActive")
    val isActive: Boolean,
)
