package com.realestate.network.adapters

import com.realestate.common.resource.Resource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Retrofit
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

/**
 * Created by van.luong
 * on 12,June,2025
 *
 * A [CallAdapter.Factory] that creates [NetworkResponseAdapter] instances for handling
 */
class NetworkResponseCallFactory private constructor(
    private val coroutineScope: CoroutineScope
) : CallAdapter.Factory() {

    override fun get(
        returnType: Type,
        annotations: Array<out Annotation>,
        retrofit: Retrofit
    ): CallAdapter<*, *>? {
        if (getRawType(returnType) != Call::class.java) {
            return null
        }

        val callType = getParameterUpperBound(0, returnType as ParameterizedType)
        if (getRawType(callType) != Resource::class.java) {
            return null
        }

        val responseType = getParameterUpperBound(0, callType as ParameterizedType)
        return NetworkResponseAdapter<Any>(responseType, coroutineScope)
    }

    companion object {
        /**
         * Creates a [NetworkResponseCallFactory] instance with the provided [CoroutineScope].
         *
         * @param coroutineScope The [CoroutineScope] to be used for network operations.
         * @return A new instance of [NetworkResponseCallFactory].
         */
        fun create(
            coroutineScope: CoroutineScope = CoroutineScope(Dispatchers.IO)
        ): NetworkResponseCallFactory = NetworkResponseCallFactory(coroutineScope)
    }
}