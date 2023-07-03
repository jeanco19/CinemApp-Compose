package com.jean.cinemappcompose.presentation.navigation.navgraph

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Movie
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.jean.cinemappcompose.R
import com.jean.cinemappcompose.presentation.movie.screen.FavoriteScreen
import com.jean.cinemappcompose.presentation.movie.screen.MovieScreen
import com.jean.cinemappcompose.presentation.navigation.Graphs
import com.jean.cinemappcompose.presentation.navigation.Routes
import com.jean.cinemappcompose.presentation.profile.screen.ProfileScreen

@Composable
fun HomeBottomBarNavGraph(navController: NavHostController) {

    NavHost(
        navController = navController,
        route = Graphs.HOME,
        startDestination = HomeBottomBarScreen.Movies.route
    ) {

        composable(route = HomeBottomBarScreen.Movies.route) {
            MovieScreen()
        }

        composable(route = HomeBottomBarScreen.Favorites.route) {
            FavoriteScreen()
        }

        composable(route = HomeBottomBarScreen.Profile.route) {
            ProfileScreen()
        }

    }

}

sealed class HomeBottomBarScreen(
    val route: String,
    @StringRes val title: Int,
    val icon: ImageVector
) {

    object Movies : HomeBottomBarScreen(
        route = Routes.MOVIES_ROUTE,
        title = R.string.movie_list_option,
        icon = Icons.Default.Movie
    )

    object Favorites : HomeBottomBarScreen(
        route = Routes.FAVORITES_ROUTE,
        title = R.string.favorite_list_option,
        icon = Icons.Default.Favorite
    )

    object Profile : HomeBottomBarScreen(
        route = Routes.PROFILE_ROUTE,
        title = R.string.profile_option,
        icon = Icons.Default.Person
    )

}