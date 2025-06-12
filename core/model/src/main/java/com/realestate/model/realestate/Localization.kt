package com.realestate.model.realestate

/**
 * Created by van.luong
 * on 12,June,2025
 */

/**
 * Represents a real estate localization.
 */
data class Localization(
    val primary: String,
    val de: De
)

/**
 * Represents the German localization of a real estate property.
 */
data class De(
    val attachments: List<Attachments>,
    val text: Text,
)

/**
 * Represents an attachment in the real estate localization.
 *
 * @property url The URL of the attachment.
 */
data class Attachments(
    val url: String
)

/**
 * Represents the text content in the real estate localization.
 *
 * @property title The title of the real estate property.
 */
data class Text(
    val title: String
)