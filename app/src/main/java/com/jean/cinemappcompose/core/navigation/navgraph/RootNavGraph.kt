package com.jean.cinemappcompose.core.navigation.navgraph

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.jean.cinemappcompose.auth.presentation.restart_password.RestartPasswordScreen
import com.jean.cinemappcompose.auth.presentation.signin.SignInScreen
import com.jean.cinemappcompose.auth.presentation.signup.SignUpScreen
import com.jean.cinemappcompose.auth.presentation.welcome.WelcomeScreen
import com.jean.cinemappcompose.home.presentation.HomeScreen
import com.jean.cinemappcompose.core.navigation.Graphs
import com.jean.cinemappcompose.core.navigation.Routes

@Composable
fun RootNavGraph(
    navController: NavHostController,
    startDestination: String = RootScreen.Welcome.route
) {
    NavHost(
        navController = navController,
        route = Graphs.ROOT,
        startDestination = startDestination
    ) {

        composable(RootScreen.Welcome.route) {
            WelcomeScreen(
                signInClicked = {
                    navController.navigate(RootScreen.SignIn.route) {
                        popUpTo(route = RootScreen.Welcome.route) { inclusive = true }
                    }
                },
                signUpClicked = {
                    navController.navigate(route = RootScreen.SignUp.route) {
                        popUpTo(route = RootScreen.Welcome.route) { inclusive = true }
                    }
                },
                navigateToHome = {
                    navController.navigate(route = RootScreen.Home.route) {
                        popUpTo(route = RootScreen.SignIn.route) { inclusive = true }
                    }
                }
            )
        }

        composable(RootScreen.SignIn.route) {
            SignInScreen(
                navigateToHome = {
                    navController.navigate(route = RootScreen.Home.route) {
                        popUpTo(route = RootScreen.SignIn.route) { inclusive = true }
                    }
                },
                navigateToSignUp = {
                    navController.navigate(route = RootScreen.SignUp.route)
                },
                navigateToRecoverPassword = {
                    navController.navigate(route = RootScreen.RestartPassword.route)
                }
            )
        }

        composable(RootScreen.SignUp.route) {
            SignUpScreen(
                navigateToSignIn = {
                    navController.navigate(route = RootScreen.SignIn.route)
                }
            )
        }

        composable(RootScreen.RestartPassword.route) {
            RestartPasswordScreen(
                navigateToSignIn = {
                    navController.navigate(route = RootScreen.SignIn.route) {
                        popUpTo(route = RootScreen.RestartPassword.route) { inclusive = true }
                    }
                }
            )
        }

        composable(route = RootScreen.Home.route) {
            HomeScreen(navigateToSignIn = {
                navController.navigate(route = RootScreen.SignIn.route) {
                    popUpTo(route = RootScreen.Home.route) { inclusive = true }
                }
            })
        }

    }
}

sealed class RootScreen(val route: String) {
    object Welcome : RootScreen(Routes.WELCOME_ROUTE)
    object SignIn : RootScreen(Routes.SIGN_IN_ROUTE)
    object SignUp : RootScreen(Routes.SIGN_UP_ROUTE)
    object RestartPassword : RootScreen(Routes.RESTART_PASSWORD_ROUTE)
    object Home : RootScreen(Routes.HOME_ROUTE)
}