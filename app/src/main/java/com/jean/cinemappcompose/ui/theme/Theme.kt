package com.jean.cinemappcompose.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import com.google.accompanist.systemuicontroller.rememberSystemUiController

private val DarkColorPalette = darkColors(
    primary = md_theme_dark_primary,
    primaryVariant = md_theme_dark_secondary,
    secondary = md_theme_dark_secondary,
    secondaryVariant = md_theme_dark_secondary,
    background = md_theme_dark_background,
    surface = md_theme_dark_surface,
    onPrimary = md_theme_dark_on_primary,
    onSecondary = md_theme_dark_on_secondary,
    onBackground = md_theme_dark_on_background,
    onSurface = md_theme_dark_on_surface,
    error = md_theme_dark_error
)

private val LightColorPalette = lightColors(
    primary = md_theme_light_primary,
    primaryVariant = md_theme_light_secondary,
    secondary = md_theme_light_secondary,
    secondaryVariant = md_theme_light_secondary,
    background = md_theme_light_background,
    surface = md_theme_light_surface,
    onPrimary = md_theme_light_on_primary,
    onSecondary = md_theme_light_on_secondary,
    onBackground = md_theme_light_on_background,
    onSurface = md_theme_light_on_surface,
    error = md_theme_light_error
)

@Composable
fun CinemAppComposeTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val systemUiController = rememberSystemUiController()

    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    SideEffect {
        systemUiController.setStatusBarColor(
            color = colors.primary,
            darkIcons = false
        )
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}