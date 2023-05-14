package com.jean.cinemappcompose.presentation.navigation

import androidx.navigation.NavController

fun navigateTo(navController: NavController, destination: DestinationScreen) {
    navController.navigate(destination.route) {
        popUpTo(destination.route)
        launchSingleTop = true
    }
}