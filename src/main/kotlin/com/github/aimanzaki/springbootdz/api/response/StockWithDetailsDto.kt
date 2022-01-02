package com.github.aimanzaki.springbootdz.api.response

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import java.time.OffsetDateTime

class StockWithDetailsDto(
    @JsonInclude(JsonInclude.Include.NON_NULL)
    val productCode: String?,
    val yesterdayQuantityBalance: Int? = 0,
    val quantityIn: Int?,
    val modalPrice: Double?,
    val quantityBalance: Int?,
    val sale: Double?,
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("stockDate")
    var stockDate: OffsetDateTime?,

)
