package com.realestate.network.dto.realestate

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Created by van.luong
 * on 12,June,2025
 */

/**
 * Data Transfer Object (DTO) for localization information.
 */
@JsonClass(generateAdapter = true)
data class LocalizationDto(
    @field:Json(name = "primary") val primary: String,
    @field:Json(name = "de") val locale: LocaleDto,
)

/**
 * Data Transfer Object (DTO) for German localization details.
 *
 * @see AttachmentsDto
 * @see TextDto
 */
@JsonClass(generateAdapter = true)
data class LocaleDto(
    @field:Json(name = "attachments") val attachments: List<AttachmentsDto>,
    @field:Json(name = "text") val text: TextDto
)

/**
 * Data Transfer Object (DTO) for attachments in localization.
 *
 * @see TextDto
 * @see LocaleDto
 * @see LocalizationDto
 */
@JsonClass(generateAdapter = true)
data class AttachmentsDto(
    @field:Json(name = "url") val url: String
)

/**
 * Data Transfer Object (DTO) for text information in localization.
 *
 * @see AttachmentsDto
 * @see LocaleDto
 * @see LocalizationDto
 */
@JsonClass(generateAdapter = true)
data class TextDto(
    @field:Json(name = "title") val title: String
)
