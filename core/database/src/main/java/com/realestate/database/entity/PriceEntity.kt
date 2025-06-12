package com.realestate.database.entity

import androidx.room.Embedded
import com.realestate.model.realestate.Price
import com.realestate.model.realestate.Buy

/**
 * Created by van.luong
 * on 12,June,2025
 */
/**
 * Entity class for [Price] to store in the database.
 *
 * @property currency The currency of the price, e.g., "USD", "EUR".
 * @property buy The buying details of the property.
 */
data class PriceEntity(
    val currency: String,
    @Embedded(prefix = "buy_") val buy: BuyEntity
)

/**
 * Entity class for [Buy] to store in the database.
 *
 * @property area The area of the property in square meters, nullable if not applicable.
 * @property price The price of the property in the specified currency.
 * @property interval The payment interval, nullable if not applicable.
 */

data class BuyEntity(
    val area: String? = null,
    val price: Long,
    val interval: String? = null
)
