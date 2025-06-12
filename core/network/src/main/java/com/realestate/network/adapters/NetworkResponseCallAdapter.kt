package com.realestate.network.adapters

import com.realestate.common.resource.Resource
import kotlinx.coroutines.CoroutineScope
import retrofit2.Call
import retrofit2.CallAdapter
import java.lang.reflect.Type

/**
 * Created by van.luong
 * on 12,June,2025
 *
 * A [CallAdapter] that adapts a Retrofit [Call] to a [Resource] type.
 */
class NetworkResponseAdapter<T : Any>(
    private val responseType: Type,
    private val coroutineScope: CoroutineScope
) : CallAdapter<T, Call<Resource<T>>> {

    override fun responseType(): Type = responseType

    override fun adapt(call: Call<T>): Call<Resource<T>> {
        return NetworkResponseCall(call, coroutineScope)
    }
}