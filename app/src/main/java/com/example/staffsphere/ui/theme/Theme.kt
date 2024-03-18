package com.example.staffsphere.ui.theme


import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorPalette = darkColors(
    primary = Color(0xFF2c9fff),
    primaryVariant = Color(0xFF2c9fff),
    secondary = Color(0xFF0061a4),
    background = Color(0xFF2c9fff)
)


@Composable
fun StaffSphereTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colors = DarkColorPalette,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}