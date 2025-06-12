package com.realestate.database.entity

import com.realestate.model.realestate.Attachments
import com.realestate.model.realestate.Locale
import com.realestate.model.realestate.Localization
import com.realestate.model.realestate.Text

/**
 * Created by van.luong
 * on 12,June,2025
 */

/**
 * Represents a real estate [Localization] entity for database storage.
 *
 * @property primary The primary language of the localization.
 * @property locale The German localization details.
 */
data class LocalizationEntity(
    val primary: String,
    val locale: LocaleEntity
)

/**
 * Represents the German localization [Locale] of a real estate property.
 *
 * @property attachments The list of attachments in the German localization.
 * @property text The text content in the German localization.
 */
data class LocaleEntity(
    val attachments: List<AttachmentsEntity>,
    val text: TextEntity
)

/**
 * Represents an [Attachments] in the real estate localization.
 *
 * @property url The URL of the attachment.
 */
data class AttachmentsEntity(
    val url: String
)

/**
 * Represents the [Text] content in the real estate localization.
 *
 * @property title The title of the real estate property.
 */
data class TextEntity(
    val title: String
)
