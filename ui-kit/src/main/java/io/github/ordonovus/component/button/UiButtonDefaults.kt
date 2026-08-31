package io.github.ordonovus.component.button

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ButtonElevation
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape

/**
 * Provides default values for the button components included in Compose UI Kit.
 *
 * This object centralizes common button configuration so that all button
 * variants can share a consistent visual language while still allowing
 * individual values to be overridden when necessary.
 */
object UiButtonDefaults {

    /**
     * Default shape used by [UiButton].
     */
    val Shape: Shape
        @Composable get() = ButtonDefaults.shape

    /**
     * Default shape used by [UiFilledTonalButton].
     */
    val FilledTonalShape: Shape
        @Composable get() = ButtonDefaults.filledTonalShape

    /**
     * Default shape used by [UiElevatedButton].
     */
    val ElevatedShape: Shape
        @Composable get() = ButtonDefaults.elevatedShape

    /**
     * Default shape used by [UiOutlinedButton].
     */
    val OutlinedShape: Shape
        @Composable get() = ButtonDefaults.outlinedShape

    /**
     * Default shape used by [UiTextButton].
     */
    val TextShape: Shape
        @Composable get() = ButtonDefaults.textShape

    /**
     * Default minimum width applied to buttons.
     */
    val MinWidth = ButtonDefaults.MinWidth

    /**
     * Default minimum height applied to buttons.
     */
    val MinHeight = ButtonDefaults.MinHeight

    /**
     * Default icon size used inside buttons.
     */
    val IconSize = ButtonDefaults.IconSize

    /**
     * Default spacing between an icon and text inside buttons.
     */
    val IconSpacing = ButtonDefaults.IconSpacing

    /**
     * Default content padding used by buttons.
     */
    val ContentPadding: PaddingValues = ButtonDefaults.ContentPadding

    /**
     * Creates the default colors for [UiButton].
     *
     * @param containerColor Enabled container color.
     * @param contentColor Enabled content color.
     * @param disabledContainerColor Disabled container color.
     * @param disabledContentColor Disabled content color.
     *
     * @return A [ButtonColors] configuration for enabled and disabled states.
     */
    @Composable
    fun colors(
        containerColor: Color = Color.Unspecified,
        contentColor: Color = Color.Unspecified,
        disabledContainerColor: Color = Color.Unspecified,
        disabledContentColor: Color = Color.Unspecified
    ): ButtonColors {
        return ButtonDefaults.buttonColors(
            containerColor = containerColor,
            contentColor = contentColor,
            disabledContainerColor = disabledContainerColor,
            disabledContentColor = disabledContentColor
        )
    }

    /**
     * Creates the default colors for [UiFilledTonalButton].
     *
     * @return A [ButtonColors] configuration for enabled and disabled states.
     */
    @Composable
    fun filledTonalColors(): ButtonColors {
        return ButtonDefaults.filledTonalButtonColors()
    }

    /**
     * Creates the default colors for [UiElevatedButton].
     *
     * @return A [ButtonColors] configuration for enabled and disabled states.
     */
    @Composable
    fun elevatedColors(): ButtonColors {
        return ButtonDefaults.elevatedButtonColors()
    }

    /**
     * Creates the default colors for [UiOutlinedButton].
     *
     * @return A [ButtonColors] configuration for enabled and disabled states.
     */
    @Composable
    fun outlinedColors(): ButtonColors {
        return ButtonDefaults.outlinedButtonColors()
    }

    /**
     * Creates the default border for [UiOutlinedButton].
     *
     * The border adapts its color to the enabled and disabled states.
     *
     * @param enabled Whether the button is enabled.
     *
     * @return The default [BorderStroke] used by [UiOutlinedButton].
     */
    @Composable
    fun outlinedBorder(enabled: Boolean = true): BorderStroke {
        return ButtonDefaults.outlinedButtonBorder(enabled)
    }

    /**
     * Creates the default colors for [UiTextButton].
     *
     * @return A [ButtonColors] configuration for enabled and disabled states.
     */
    @Composable
    fun textColors(): ButtonColors {
        return ButtonDefaults.textButtonColors()
    }

    /**
     * Creates the default elevation for [UiButton].
     *
     * @return A [ButtonElevation] configuration for the button.
     */
    @Composable
    fun elevation(): ButtonElevation {
        return ButtonDefaults.buttonElevation()
    }

    /**
     * Creates the default elevation for [UiFilledTonalButton].
     *
     * @return A [ButtonElevation] configuration for the button.
     */
    @Composable
    fun filledTonalElevation(): ButtonElevation {
        return ButtonDefaults.filledTonalButtonElevation()
    }

    /**
     * Creates the default elevation for [UiElevatedButton].
     *
     * @return A [ButtonElevation] configuration for the button.
     */
    @Composable
    fun elevatedElevation(): ButtonElevation {
        return ButtonDefaults.elevatedButtonElevation()
    }

}