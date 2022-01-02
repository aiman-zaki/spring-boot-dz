package com.github.aimanzaki.springbootdz.api.response

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonProperty
import java.time.OffsetDateTime
import java.util.UUID

data class StockDto(

    @JsonProperty("id")
    var id: UUID,

    @JsonProperty("branchId")
    var branchId: UUID,

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXX")
    @JsonProperty("stockDate")
    var stockDate: OffsetDateTime,

    @JsonProperty("stockDetails")
    var stockWithDetails: List<StockWithDetailsDto>,

)
