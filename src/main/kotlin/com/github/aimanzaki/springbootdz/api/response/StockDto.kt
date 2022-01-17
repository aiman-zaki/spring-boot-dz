package com.github.aimanzaki.springbootdz.api.response

import com.fasterxml.jackson.annotation.JsonProperty
import java.time.LocalDate
import java.util.UUID

data class StockDto(

    @JsonProperty("id")
    var id: UUID,

    @JsonProperty("branchId")
    var branchId: UUID,

    @JsonProperty("stockDate")
    var stockDate: LocalDate,

    @JsonProperty("stockDetails")
    var stockWithDetails: List<StockWithDetailsDto>,

)
