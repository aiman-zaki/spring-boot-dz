package com.github.aimanzaki.springbootdz.dto

import com.fasterxml.jackson.annotation.JsonProperty
import com.github.aimanzaki.springbootdz.enums.Authorities
import java.time.OffsetDateTime
import java.util.UUID

data class UserDto(

    @JsonProperty("id")
    val id: UUID,

    @JsonProperty("name")
    val name: String,

    @JsonProperty("email")
    val email: String,

    @JsonProperty("password")
    val password: String,

    @JsonProperty("authorities")
    val authorities: List<Authorities>,

    @JsonProperty("createdAt")
    val createdAt: OffsetDateTime,

    @JsonProperty("updatedAt")
    val updatedAt: OffsetDateTime,

)
