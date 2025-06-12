package com.realestate.database.converters

import androidx.room.TypeConverter
import com.realestate.database.entity.LocaleEntity
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

/**
 * Created by van.luong
 * on 13,June,2025
 */
class LocaleEntityConverter {
    private val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
    private val adapter = moshi.adapter(LocaleEntity::class.java)

    @TypeConverter
    fun fromLocaleEntity(entity: LocaleEntity): String {
        return adapter.toJson(entity)
    }

    @TypeConverter
    fun toLocaleEntity(json: String): LocaleEntity {
        return adapter.fromJson(json)!!
    }
}