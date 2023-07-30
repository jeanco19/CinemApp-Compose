package com.jean.cinemappcompose.core.navigation.navgraph

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.jean.cinemappcompose.auth.presentation.restart_password.RestartPasswordScreen
import com.jean.cinemappcompose.auth.presentation.signin.SignInScreen
import com.jean.cinemappcompose.auth.presentation.signup.SignUpScreen
import com.jean.cinemappcompose.auth.presentation.welcome.WelcomeScreen
import com.jean.cinemappcompose.core.navigation.Graphs
import com.jean.cinemappcompose.core.navigation.Routes

fun NavGraphBuilder.authNavGraph(navController: NavHostController) {

    navigation(
        route = Graphs.AUTHENTICATION,
        startDestination = AuthScreen.Welcome.route
    ) {

        composable(AuthScreen.Welcome.route) {
            WelcomeScreen(
                signInClicked = {
                    navController.navigate(AuthScreen.SignIn.route)
                },
                signUpClicked = {
                    navController.navigate(route = AuthScreen.SignUp.route)
                },
                navigateToHome = {
                    navController.navigate(route = RootScreen.Home.route) {
                        popUpTo(route = Graphs.AUTHENTICATION) { inclusive = true }
                    }
                }
            )
        }

        composable(AuthScreen.SignIn.route) {
            SignInScreen(
                navigateToHome = {
                    navController.navigate(route = RootScreen.Home.route) {
                        popUpTo(route = AuthScreen.SignIn.route) { inclusive = true }
                    }
                },
                navigateToSignUp = {
                    navController.navigate(route = AuthScreen.SignUp.route)
                },
                navigateToRecoverPassword = {
                    navController.navigate(route = AuthScreen.RestartPassword.route)
                }
            )
        }

        composable(AuthScreen.SignUp.route) {
            SignUpScreen(
                navigateToSignIn = {
                    navController.navigate(route = AuthScreen.SignIn.route) {
                        popUpTo(route = AuthScreen.SignUp.route) { inclusive = true }
                    }
                }
            )
        }

        composable(AuthScreen.RestartPassword.route) {
            RestartPasswordScreen(
                navigateToSignIn = {
                    navController.navigate(route = AuthScreen.SignIn.route) {
                        popUpTo(route = AuthScreen.RestartPassword.route) { inclusive = true }
                    }
                }
            )
        }

    }

}

sealed class AuthScreen(val route: String) {
    object Welcome : AuthScreen(Routes.WELCOME_ROUTE)
    object SignIn : AuthScreen(Routes.SIGN_IN_ROUTE)
    object SignUp : AuthScreen(Routes.SIGN_UP_ROUTE)
    object RestartPassword : AuthScreen(Routes.RESTART_PASSWORD_ROUTE)
}