package com.realestate.common.resource

import com.realestate.common.error.ApiError

/**
 * Created by van.luong
 * on 12,June,2025
 *
 * A sealed class representing the state of a resource.
 */
sealed class Resource<out T> {
    data object Loading : Resource<Nothing>()
    data class Success<T>(val body: T) : Resource<T>()
    data class DataError(val error: Throwable) : Resource<Nothing>()
    data class ServerError(val error: ApiError) : Resource<Nothing>()
}