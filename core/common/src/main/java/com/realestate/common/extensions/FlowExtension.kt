package com.realestate.common.extensions

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * Created by van.luong
 * on 14,June,2025
 */

/**
 * Extension function to convert any object to a Flow that emits the object.
 *
 * @return A Flow that emits the object.
 */
fun <T> T.asFlow(): Flow<T> = flow {
    emit(this@asFlow)
}