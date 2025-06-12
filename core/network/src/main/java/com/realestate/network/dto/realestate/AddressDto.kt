package com.realestate.network.dto.realestate

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Created by van.luong
 * on 12,June,2025
 *
 * Data Transfer Object (DTO) for Address in Real Estate application.
 */
@JsonClass(generateAdapter = true)
data class AddressDto(
    @field:Json(name = "country") val country: String,
    @field:Json(name = "locality") val locality: String,
    @field:Json(name = "postalCode") val postalCode: String,
    @field:Json(name = "region") val region: String,
    @field:Json(name = "street") val street: String? = null,
)