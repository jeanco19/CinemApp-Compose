package com.jean.cinemappcompose.home.presentation.component

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.jean.cinemappcompose.core.navigation.navgraph.HomeBottomBarScreen

@Composable
fun BottomBar(navController: NavHostController) {

    val screens = listOf(
        HomeBottomBarScreen.Movies,
        HomeBottomBarScreen.Favorites,
        HomeBottomBarScreen.Profile
    )

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    val bottomBarDestination = screens.any { it.route == currentDestination?.route }

    if (bottomBarDestination) {
        BottomNavigation {
            screens.forEach {screen ->
                AddItem(
                    screen = screen,
                    currentDestination = currentDestination,
                    navController = navController
                )
            }
        }
    }

}

@Composable
fun RowScope.AddItem(
    screen: HomeBottomBarScreen,
    currentDestination: NavDestination?,
    navController: NavHostController
) {
    BottomNavigationItem(
        label = {
            Text(text = stringResource(id = screen.title))
        },
        icon = {
            Icon(
                imageVector = screen.icon,
                contentDescription = "Navigation icon"
            )
        },
        selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
        unselectedContentColor = LocalContentColor.current.copy(alpha = ContentAlpha.disabled),
        onClick = {
            navController.navigate(screen.route) {
                popUpTo(navController.graph.findStartDestination().id)
                launchSingleTop = true
            }
        }
    )
}