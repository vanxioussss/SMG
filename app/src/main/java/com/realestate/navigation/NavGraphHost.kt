package com.realestate.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.realestate.listproperties.navigation.PropertyListRoute
import com.realestate.listproperties.navigation.propertyListScreen

/**
 * Created by van.luong
 * on 14,June,2025
 *
 * This is the main navigation graph for the Real Estate application.
 */
@Composable
fun NavGraphHost(
    modifier: Modifier = Modifier,
    startDestination: Any = PropertyListRoute,
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        propertyListScreen()
    }
}