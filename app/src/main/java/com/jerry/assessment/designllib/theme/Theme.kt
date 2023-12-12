package com.jerry.assessment.designllib.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat


private val LightColorScheme = lightColorScheme(

    primary = hm_theme_light_primary,
    onPrimary = hm_theme_light_onPrimary,
    primaryContainer = hm_theme_light_primaryContainer,
    onPrimaryContainer = hm_theme_light_onPrimaryContainer,
    secondary = hm_theme_light_secondary,
    onSecondary = hm_theme_light_onSecondary,
    secondaryContainer = hm_theme_light_secondaryContainer,
    onSecondaryContainer = hm_theme_light_onSecondaryContainer,
    tertiary = hm_theme_light_tertiary,
    onTertiary = hm_theme_light_onTertiary,
    tertiaryContainer = hm_theme_light_tertiaryContainer,
    onTertiaryContainer = hm_theme_light_onTertiaryContainer,
    error = hm_theme_light_error,
    errorContainer = hm_theme_light_errorContainer,
    onError = hm_theme_light_onError,
    onErrorContainer = hm_theme_light_onErrorContainer,
    background = hm_theme_light_background,
    onBackground = hm_theme_light_onBackground,
    surface = hm_theme_light_surface,
    onSurface = hm_theme_light_onSurface,
    surfaceVariant = hm_theme_light_surfaceVariant,
    onSurfaceVariant = hm_theme_light_onSurfaceVariant,
    outline = hm_theme_light_outline,
    inverseOnSurface = hm_theme_light_inverseOnSurface,
    inverseSurface = hm_theme_light_inverseSurface,
)
private val DarkColorScheme = darkColorScheme(

    primary = hm_theme_dark_primary,
    onPrimary = hm_theme_dark_onPrimary,
    primaryContainer = hm_theme_dark_primaryContainer,
    onPrimaryContainer = hm_theme_dark_onPrimaryContainer,
    secondary = hm_theme_dark_secondary,
    onSecondary = hm_theme_dark_onSecondary,
    secondaryContainer = hm_theme_dark_secondaryContainer,
    onSecondaryContainer = hm_theme_dark_onSecondaryContainer,
    tertiary = hm_theme_dark_tertiary,
    onTertiary = hm_theme_dark_onTertiary,
    tertiaryContainer = hm_theme_dark_tertiaryContainer,
    onTertiaryContainer = hm_theme_dark_onTertiaryContainer,
    error = hm_theme_dark_error,
    errorContainer = hm_theme_dark_errorContainer,
    onError = hm_theme_dark_onError,
    onErrorContainer = hm_theme_dark_onErrorContainer,
    background = hm_theme_dark_background,
    onBackground = hm_theme_dark_onBackground,
    surface = hm_theme_dark_surface,
    onSurface = hm_theme_dark_onSurface,
    surfaceVariant = hm_theme_dark_surfaceVariant,
    onSurfaceVariant = hm_theme_dark_onSurfaceVariant,
    outline = hm_theme_dark_outline,
    inverseOnSurface = hm_theme_dark_inverseOnSurface,
    inverseSurface = hm_theme_dark_inverseSurface,
)

@Composable
fun AssessmentTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.primary.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}