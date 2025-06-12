package com.realestate.network.dto.realestate

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Created by van.luong
 * on 12,June,2025
 */

/**
 * Data Transfer Object (DTO) for price information.
 *
 * @see BuyDto
 */
@JsonClass(generateAdapter = true)
data class PriceDto(
    @field:Json(name = "currency") val currency: String,
    @field:Json(name = "buy") val buy: BuyDto,
)

/**
 * Data Transfer Object (DTO) for buy price details.
 *
 * @property area The area associated with the price, if applicable.
 * @property price The price amount in the specified currency.
 * @property interval The interval for the price, if applicable (e.g., monthly, yearly).
 */
@JsonClass(generateAdapter = true)
data class BuyDto(
    @field:Json(name = "area") val area: String? = null,
    @field:Json(name = "price") val price: Long,
    @field:Json(name = "interval") val interval: String? = null,
)