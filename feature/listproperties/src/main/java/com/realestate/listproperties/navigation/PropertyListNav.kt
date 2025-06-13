package com.realestate.listproperties.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.realestate.listproperties.PropertyListRoute
import kotlinx.serialization.Serializable

/**
 * Created by van.luong
 * on 14,June,2025
 */

/**
 * This is the route for the Property List screen.
 */
@Serializable
data object PropertyListRoute

fun NavController.navigateToPropertyListRoute(navOptions: NavOptions) =
    navigate(route = PropertyListRoute, navOptions)

fun NavGraphBuilder.propertyListScreen() {
    composable<PropertyListRoute> {
        PropertyListRoute()
    }
}