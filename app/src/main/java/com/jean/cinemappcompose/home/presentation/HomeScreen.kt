package com.jean.cinemappcompose.home.presentation

import android.annotation.SuppressLint
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.jean.cinemappcompose.core.navigation.navgraph.HomeBottomBarNavGraph
import com.jean.cinemappcompose.home.presentation.component.BottomBar

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun HomeScreen(
    navController: NavHostController = rememberNavController(),
    navigateToSignIn: () -> Unit
) {
    Scaffold(bottomBar = {
        BottomBar(navController = navController)
    }) {
        HomeBottomBarNavGraph(
            navController = navController,
            navigateToSignIn = navigateToSignIn
        )
    }
}