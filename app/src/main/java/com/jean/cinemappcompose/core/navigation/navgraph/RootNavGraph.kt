package com.jean.cinemappcompose.core.navigation.navgraph

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.jean.cinemappcompose.home.presentation.HomeScreen
import com.jean.cinemappcompose.core.navigation.Graphs
import com.jean.cinemappcompose.core.navigation.Routes

@Composable
fun RootNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        route = Graphs.ROOT,
        startDestination = Graphs.AUTHENTICATION
    ) {

        authNavGraph(navController = navController)

        composable(route = RootScreen.Home.route) {
            HomeScreen()
        }

    }
}

sealed class RootScreen(val route: String) {
    object Home : RootScreen(Routes.HOME_ROUTE)
}