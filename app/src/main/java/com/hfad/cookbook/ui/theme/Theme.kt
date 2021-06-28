package com.hfad.cookbook.ui.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val LightColors = lightColors(
    primary = Orange,
    primaryVariant = DarkOrange,
    onPrimary = Color.White,
    secondary = Blue,
    secondaryVariant = DarkBlue,
    onSecondary = Color.White
)

@Composable
fun CookbookTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colors = LightColors,
        typography = CookbookTypography,
        shapes = CookbookShapes,
        content = content
    )
}