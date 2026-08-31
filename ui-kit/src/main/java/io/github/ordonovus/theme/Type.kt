package io.github.ordonovus.theme

import androidx.compose.material3.Typography

/**
 * Typography system used by Compose UI Kit.
 *
 * The base scale follows Material 3 typography roles and can be customized
 * centrally without requiring changes to individual components.
 */
val UiTypography = Typography()

/**
 * Typography style intended for the main display content of a screen.
 */
val UiTypographyDisplay = UiTypography.displayLarge

/**
 * Typography style intended for primary screen or section headings.
 */
val UiTypographyHeading = UiTypography.headlineLarge

/**
 * Typography style intended for titles of content sections.
 */
val UiTypographyTitle = UiTypography.titleLarge

/**
 * Typography style intended for primary body content.
 */
val UiTypographyBody = UiTypography.bodyLarge

/**
 * Typography style intended for secondary body content.
 */
val UiTypographyBodySecondary = UiTypography.bodyMedium

/**
 * Typography style intended for labels, buttons, and compact UI content.
 */
val UiTypographyLabel = UiTypography.labelLarge