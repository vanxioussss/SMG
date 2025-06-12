package com.realestate.network.adapters

import com.realestate.common.error.ApiErrorMapper
import com.realestate.common.resource.Resource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import okhttp3.Request
import okio.Timeout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.HttpException
import retrofit2.Response
import retrofit2.awaitResponse

/**
 * Created by van.luong
 * on 12,June,2025
 *
 * A Retrofit Call adapter that transforms the response into a Resource type.
 */
class NetworkResponseCall<T : Any>(
    private val delegate: Call<T>,
    private val coroutineScope: CoroutineScope,
    private val errorMapper: ApiErrorMapper = NetworkApiErrorMapper()
) : Call<Resource<T>> {

    override fun enqueue(callback: Callback<Resource<T>>) {
        coroutineScope.launch {
            try {
                val response = delegate.awaitResponse()
                val result = if (response.isSuccessful) {
                    Resource.Success(response.body()!!)
                } else {
                    Resource.ServerError(
                        errorMapper.mapApiError(HttpException(response))
                    )
                }
                callback.onResponse(this@NetworkResponseCall, Response.success(result))

            } catch (e: Exception) {
                val mappedError = errorMapper.mapApiError(e)
                val result = Resource.ServerError(mappedError)
                callback.onResponse(this@NetworkResponseCall, Response.success(result))
            }
        }
    }

    override fun execute(): Response<Resource<T>> =
        runBlocking(coroutineScope.coroutineContext) {
            try {
                val response = delegate.execute()
                if (response.isSuccessful) {
                    val body = response.body()
                    if (body != null) {
                        Response.success(Resource.Success(body))
                    } else {
                        Response.success(
                            Resource.ServerError(
                                errorMapper.mapApiError(
                                    Exception("Empty response body")
                                )
                            )
                        )
                    }
                } else {
                    Response.success(
                        Resource.ServerError(
                            errorMapper.mapApiError(
                                HttpException(response)
                            )
                        )
                    )
                }
            } catch (e: Exception) {
                val mappedError = errorMapper.mapApiError(e)
                val result = Resource.ServerError(mappedError)
                Response.success(result)
            }
        }

    override fun clone(): Call<Resource<T>> =
        NetworkResponseCall(delegate.clone(), coroutineScope)

    override fun request(): Request = delegate.request()
    override fun timeout(): Timeout = delegate.timeout()
    override fun isExecuted(): Boolean = delegate.isExecuted
    override fun isCanceled(): Boolean = delegate.isCanceled
    override fun cancel() = delegate.cancel()
}