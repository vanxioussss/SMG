package com.realestate.database.converters

import androidx.room.TypeConverter
import com.realestate.database.entity.AttachmentsEntity
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

/**
 * Created by van.luong
 * on 12,June,2025
 *
 * Converter for handling lists of [AttachmentsEntity] in the database.
 */
class AttachmentsListConverter {
    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    private val type = Types.newParameterizedType(List::class.java, AttachmentsEntity::class.java)
    private val adapter = moshi.adapter<List<AttachmentsEntity>>(type)

    @TypeConverter
    fun fromAttachments(list: List<AttachmentsEntity>): String = adapter.toJson(list)

    @TypeConverter
    fun toAttachments(json: String): List<AttachmentsEntity> = adapter.fromJson(json) ?: emptyList()
}