package com.jean.cinemappcompose.core.navigation.navgraph

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Movie
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.jean.cinemappcompose.R
import com.jean.cinemappcompose.favorite.presentation.FavoriteScreen
import com.jean.cinemappcompose.movie.presentation.MovieScreen
import com.jean.cinemappcompose.core.navigation.Graphs
import com.jean.cinemappcompose.core.navigation.Routes
import com.jean.cinemappcompose.profile.presentation.ProfileScreen

@Composable
fun HomeBottomBarNavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    navigateToSignIn: () -> Unit
) {

    NavHost(
        modifier = modifier,
        navController = navController,
        route = Graphs.HOME,
        startDestination = HomeBottomBarScreen.Movies.route
    ) {

        composable(route = HomeBottomBarScreen.Movies.route) {
            MovieScreen(navigateToSignIn = { navigateToSignIn() })
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