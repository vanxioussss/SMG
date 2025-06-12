package com.realestate.network.adapters

import com.realestate.common.error.ApiError
import com.realestate.common.error.ApiErrorMapper
import retrofit2.HttpException
import java.io.IOException

/**
 * Created by van.luong
 * on 12,June,2025
 *
 * Mapper class to convert network-related exceptions into ApiError instances.
 */
class NetworkApiErrorMapper : ApiErrorMapper {
    override fun mapApiError(e: Throwable): ApiError {
        return when (e) {
            is IOException -> ApiError.Network("Network Connection issue")
            is HttpException -> {
                val code = e.code()
                val msg = e.message
                return when (code) {
                    in 400..499 -> ApiError.Client("Client error: $msg", code)
                    in 500..599 -> ApiError.Server("Server error: $msg", code)
                    else -> ApiError.Unexpected("Unexpected error: $msg")
                }
            }

            else -> ApiError.Unexpected("Unexpected error: ${e.localizedMessage}")
        }
    }
}