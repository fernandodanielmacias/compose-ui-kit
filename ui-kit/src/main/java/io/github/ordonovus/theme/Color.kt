package io.github.ordonovus.theme

import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color

/**
 * Default light color scheme for Compose UI Kit.
 *
 * Applications can override these colors through UiKitTheme.
 */
val UiLightColorScheme = lightColorScheme(
    primary = Color(0xFF005BC4),
    onPrimary = Color(0xFFFFFFFF),
    primaryContainer = Color(0xFFD8E6FF),
    onPrimaryContainer = Color(0xFF001A41),

    secondary = Color(0xFF465F89),
    onSecondary = Color(0xFFFFFFFF),
    secondaryContainer = Color(0xFFD9E5FF),
    onSecondaryContainer = Color(0xFF001B3F),

    tertiary = Color(0xFF52618A),
    onTertiary = Color(0xFFFFFFFF),
    tertiaryContainer = Color(0xFFE0E5FF),
    onTertiaryContainer = Color(0xFF101C43),

    background = Color(0xFFF7FAFF),
    onBackground = Color(0xFF171C26),

    surface = Color(0xFFF7FAFF),
    onSurface = Color(0xFF171C26),
    surfaceVariant = Color(0xFFDFE3EE),
    onSurfaceVariant = Color(0xFF434955),

    surfaceContainerLowest = Color(0xFFFFFFFF),
    surfaceContainerLow = Color(0xFFF0F4FC),
    surfaceContainer = Color(0xFFEAF0F9),
    surfaceContainerHigh = Color(0xFFE3EBF6),
    surfaceContainerHighest = Color(0xFFDCE5F1),

    outline = Color(0xFF737987),
    outlineVariant = Color(0xFFC3C8D5),

    error = Color(0xFFBA1A1A),
    onError = Color(0xFFFFFFFF),
    errorContainer = Color(0xFFFFDAD6),
    onErrorContainer = Color(0xFF410002)
)

/**
 * Default dark color scheme for Compose UI Kit.
 *
 * Applications can override these colors through UiKitTheme.
 */
val UiDarkColorScheme = darkColorScheme(
    primary = Color(0xFFA8C8FF),
    onPrimary = Color(0xFF003064),
    primaryContainer = Color(0xFF124786),
    onPrimaryContainer = Color(0xFFD8E6FF),

    secondary = Color(0xFFB0C6ED),
    onSecondary = Color(0xFF173153),
    secondaryContainer = Color(0xFF304A70),
    onSecondaryContainer = Color(0xFFD9E5FF),

    tertiary = Color(0xFFBEC7F5),
    onTertiary = Color(0xFF28345D),
    tertiaryContainer = Color(0xFF404C75),
    onTertiaryContainer = Color(0xFFE0E5FF),

    background = Color(0xFF0C1524),
    onBackground = Color(0xFFE2E8F3),

    surface = Color(0xFF0C1524),
    onSurface = Color(0xFFE2E8F3),
    surfaceVariant = Color(0xFF434955),
    onSurfaceVariant = Color(0xFFC3C8D5),

    surfaceContainerLowest = Color(0xFF070E19),
    surfaceContainerLow = Color(0xFF111D30),
    surfaceContainer = Color(0xFF17243A),
    surfaceContainerHigh = Color(0xFF203049),
    surfaceContainerHighest = Color(0xFF293A55),

    outline = Color(0xFF8D93A2),
    outlineVariant = Color(0xFF434955),

    error = Color(0xFFFFB4AB),
    onError = Color(0xFF690005),
    errorContainer = Color(0xFF93000A),
    onErrorContainer = Color(0xFFFFDAD6)
)
