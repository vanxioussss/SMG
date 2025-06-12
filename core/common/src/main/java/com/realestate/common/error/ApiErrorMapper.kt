package com.realestate.common.error

/**
 * Created by van.luong
 * on 12,June,2025
 *
 * Mapper interface for converting API errors into a standardized ApiError format.
 */
interface ApiErrorMapper {
    fun mapApiError(e: Throwable): ApiError
}