package com.jean.cinemappcompose.core.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.rememberNavController
import com.jean.cinemappcompose.core.navigation.navgraph.RootNavGraph
import com.jean.cinemappcompose.core.navigation.navgraph.RootScreen
import com.jean.cinemappcompose.core.presentation.viewmodel.MainViewModel
import com.jean.cinemappcompose.core.ui.theme.CinemAppComposeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        installSplashScreen()

        setContent {
            CinemAppComposeTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    RootNavGraph(
                        navController = rememberNavController(),
                        startDestination = getDestination()
                    )
                }
            }
        }

    }

    private fun getDestination(): String {
        return if (viewModel.currentUserId.isEmpty()) {
            RootScreen.Welcome.route
        } else {
            RootScreen.Home.route
        }
    }

}