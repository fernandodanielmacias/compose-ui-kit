package io.github.ordonovus.theme

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Shapes
import androidx.compose.ui.graphics.RectangleShape

/**
 * Default shape scale used by Compose UI Kit.
 *
 * The shape scale provides consistent corner treatments across components.
 */
val UiShapes = Shapes(
    extraSmall = RoundedCornerShape(UiDimensXSmall),
    small = RoundedCornerShape(UiDimensSmall),
    medium = RoundedCornerShape(UiDimensMedium),
    large = RoundedCornerShape(UiDimensLarge),
    extraLarge = RoundedCornerShape(UiDimensXLarge)
)

/**
 * Cut-corner shape scale for a more geometric visual style.
 */
val UiShapesCut = Shapes(
    extraSmall = CutCornerShape(UiDimensXSmall),
    small = CutCornerShape(UiDimensSmall),
    medium = CutCornerShape(UiDimensMedium),
    large = CutCornerShape(UiDimensLarge),
    extraLarge = CutCornerShape(UiDimensXLarge)
)

/**
 * Square shape scale with no rounded corners.
 */
val UiShapesSquare = Shapes(
    extraSmall = RoundedCornerShape(UiDimensNone),
    small = RoundedCornerShape(UiDimensNone),
    medium = RoundedCornerShape(UiDimensNone),
    large = RoundedCornerShape(UiDimensNone),
    extraLarge = RoundedCornerShape(UiDimensNone)
)

/**
 * A fully circular shape.
 *
 * Useful for circular components such as icon buttons and avatars.
 */
val UiShapeFull = CircleShape

/**
 * A rectangular shape without rounded corners.
 */
val UiShapeNone = RectangleShape