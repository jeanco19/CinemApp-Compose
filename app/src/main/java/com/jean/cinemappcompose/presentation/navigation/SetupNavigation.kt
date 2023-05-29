package com.jean.cinemappcompose.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.jean.cinemappcompose.presentation.auth.screen.*
import com.jean.cinemappcompose.presentation.movie.screen.MoviesScreen

private const val WELCOME_ROUTE = "welcome"
private const val SIGN_IN_ROUTE = "sign_in"
private const val SIGN_UP_ROUTE = "sign_up"
private const val RECOVER_PASSWORD_ROUTE = "recover_password"
private const val MOVIES_ROUTE = "movies"

@Composable
fun SetupNavigation() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = DestinationScreen.Welcome.route
    ) {

        composable(DestinationScreen.Welcome.route) {
            WelcomeScreen(
                signInClicked = {
                    navigateTo(
                        navController = navController,
                        destination = DestinationScreen.SignIn
                    )
                },
                signUpClicked = {
                    navigateTo(
                        navController =  navController,
                        destination = DestinationScreen.SignUp
                    )
                }
            )
        }

        composable(DestinationScreen.SignIn.route) {
            SignInScreen(
                signInClicked = {
                    navigateTo(
                        navController = navController,
                        destination = DestinationScreen.Movies
                    )
                },
                signUpClicked = {
                    navigateTo(
                        navController = navController,
                        destination = DestinationScreen.SignUp
                    )
                },
                recoverPasswordClicked = {
                    navigateTo(
                        navController = navController,
                        destination = DestinationScreen.RecoverPassword
                    )
                }
            )
        }

        composable(DestinationScreen.SignUp.route) {
            SignUpScreen(
                navigateToSignIn = {
                    navController.navigate(route = DestinationScreen.SignIn.route) {
                        popUpTo(route = DestinationScreen.SignUp.route) { inclusive = true }
                    }
                },
                signInClicked = {
                    navigateTo(
                        navController = navController,
                        destination = DestinationScreen.SignIn
                    )
                }
            )
        }

        composable(DestinationScreen.RecoverPassword.route) {
            RecoverPasswordScreen()
        }

        composable(DestinationScreen.Movies.route) {
            MoviesScreen()
        }

    }

}

sealed class DestinationScreen(val route: String) {
    object Welcome: DestinationScreen(WELCOME_ROUTE)
    object SignIn: DestinationScreen(SIGN_IN_ROUTE)
    object SignUp: DestinationScreen(SIGN_UP_ROUTE)
    object RecoverPassword: DestinationScreen(RECOVER_PASSWORD_ROUTE)
    object Movies: DestinationScreen(MOVIES_ROUTE)
}