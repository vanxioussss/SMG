package com.realestate.listproperties.util

import java.text.NumberFormat
import java.util.Currency
import java.util.Locale

/**
 * Created by van.luong
 * on 13,June,2025
 */

/**
 * Converts a Long value representing a price into a human-readable format.
 *
 * @return A formatted string representation of the price, localized to the default locale.
 */
fun Long.toReadablePrice(): String {
    return NumberFormat.getNumberInstance(Locale.getDefault()).format(this)
}

/**
 * Converts a currency code (e.g., "USD", "CHF") to its corresponding currency symbol.
 *
 * @return The currency symbol for the given currency code, or the original string if the code is invalid.
 */
fun String.toCurrencySymbol(): String {
    return try {
        Currency.getInstance(this).symbol.also {
            println("Currency symbol for $this: $it")
        }
    } catch (e: IllegalArgumentException) {
        this // fallback to the original string if invalid
    }
}