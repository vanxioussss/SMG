package com.realestate.model.realestate

/**
 * Created by van.luong
 * on 12,June,2025
 */

/**
 * Represents the price of a real estate property.
 */
data class Price(
    val currency: String,
    val buy: Buy
)

/**
 * Represents the buying details of a real estate property.
 *
 * @property area The area of the property in square meters, nullable if not applicable.
 * @property price The price of the property in the specified currency.
 * @property interval The payment interval, nullable if not applicable.
 */
data class Buy(
    val area: String? = null,
    val price: Long,
    val interval: String? = null
)
