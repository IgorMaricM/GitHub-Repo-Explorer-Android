package com.im.githubrepoexplorer.ui.compose

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

@Composable
fun AppTheme(
    useDarkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    // Dynamic color is available on Android 12+
    val dynamicColor = Build.VERSION.SDK_INT >= Build.VERSION_CODES.S
    val colors = when {
        dynamicColor && useDarkTheme -> dynamicDarkColorScheme(LocalContext.current)
        dynamicColor && !useDarkTheme -> dynamicLightColorScheme(LocalContext.current)
        useDarkTheme -> DarkColors
        else -> LightColors
    }

    MaterialTheme(
        colorScheme = colors,
        content = content
    )
}

private val LightColors = lightColorScheme(
    primary = Color(0xFF2196F3),        // Blue
    onPrimary = Color.White,
    secondary = Color(0xFF03DAC6),      // Teal
    onSecondary = Color.Black,
    tertiary = Color(0xFF3700B3),       // Deep Purple
    background = Color(0xFFFAFAFA),     // Almost White
    onBackground = Color(0xFF1C1B1F),   // Almost Black
    surface = Color.White,
    onSurface = Color(0xFF1C1B1F)
)

private val DarkColors = darkColorScheme(
    primary = Color(0xFF90CAF9),        // Light Blue
    onPrimary = Color.Black,
    secondary = Color(0xFF03DAC6),      // Teal
    onSecondary = Color.Black,
    tertiary = Color(0xFFBB86FC),       // Light Purple
    background = Color(0xFF1C1B1F),     // Dark Background
    onBackground = Color.White,
    surface = Color(0xFF252529),        // Dark Surface
    onSurface = Color.White
)