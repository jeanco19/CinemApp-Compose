package com.jean.cinemappcompose.presentation.navigation.navgraph

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.jean.cinemappcompose.presentation.home.screen.HomeScreen
import com.jean.cinemappcompose.presentation.navigation.Graphs
import com.jean.cinemappcompose.presentation.navigation.Routes

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