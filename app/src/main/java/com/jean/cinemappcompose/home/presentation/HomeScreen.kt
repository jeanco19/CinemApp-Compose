package com.jean.cinemappcompose.home.presentation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.jean.cinemappcompose.core.navigation.navgraph.HomeBottomBarNavGraph
import com.jean.cinemappcompose.home.presentation.component.BottomBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavHostController = rememberNavController(),
    navigateToSignIn: () -> Unit
) {
    Scaffold(bottomBar = {
        BottomBar(navController = navController)
    }) { padding ->
        HomeBottomBarNavGraph(
            modifier = Modifier.padding(padding),
            navController = navController,
            navigateToSignIn = navigateToSignIn
        )
    }
}