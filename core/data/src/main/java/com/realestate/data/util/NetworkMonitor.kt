package com.realestate.data.util

import kotlinx.coroutines.flow.Flow

/**
 * Created by van.luong
 * on 14,June,2025
 *
 * Utility for tracking network connectivity status.
 */
interface NetworkMonitor {
    val isOnline: Flow<Boolean>
}