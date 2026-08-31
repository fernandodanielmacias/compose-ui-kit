package io.github.ordonovus.component.button

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonElevation
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape

/**
 * A filled button for high-emphasis actions.
 *
 * Use [UiButton] for the primary action of a screen, dialog, card, or
 * other content section.
 *
 * The button uses the default shape, colors, elevation, and content
 * padding provided by [UiButtonDefaults]. Each value can be overridden
 * for specialized use cases.
 *
 * @param onClick Called when the user clicks the button.
 * @param modifier Modifier applied to the button.
 * @param enabled Controls whether the button can be interacted with.
 * When `false`, the button uses its disabled state.
 * @param shape Shape applied to the button container.
 * @param colors Colors used by the button for its enabled and disabled states.
 * @param elevation Elevation applied to the button according to its state.
 * @param border Optional border drawn around the button.
 * @param contentPadding Padding applied between the button container and its content.
 * @param content Content displayed inside the button.
 *
 * Example:
 * ```
 * UiButton(
 *     onClick = { saveData() }
 * ) {
 *     Text("Save")
 * }
 * ```
 *
 * Example with an icon:
 * ```
 * UiButton(
 *     onClick = {
 *         saveData()
 *     }
 * ) {
 *     Icon(
 *         imageVector = Icons.Default.Save,
 *         contentDescription = null
 *     )
 *
 *     Spacer(
 *         modifier = Modifier.width(UiButtonDefaults.IconSpacing)
 *     )
 *
 *     Text("Save")
 * }
 * ```
 */
@Composable
fun UiButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    shape: Shape = UiButtonDefaults.Shape,
    colors: ButtonColors = UiButtonDefaults.colors(),
    elevation: ButtonElevation? = UiButtonDefaults.elevation(),
    border: BorderStroke? = null,
    contentPadding: PaddingValues = UiButtonDefaults.ContentPadding,
    content: @Composable RowScope.() -> Unit
) {
    Button(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        shape = shape,
        colors = colors,
        elevation = elevation,
        border = border,
        contentPadding = contentPadding,
        content = content
    )
}

/**
 * A filled tonal button for actions that require less emphasis than
 * a standard filled button.
 *
 * @param onClick Called when the user clicks the button.
 * @param modifier Modifier applied to the button.
 * @param enabled Controls whether the button can be interacted with.
 * @param shape Shape applied to the button container.
 * @param colors Colors used by the button for its enabled and disabled states.
 * @param elevation Elevation applied to the button according to its state.
 * @param border Optional border drawn around the button.
 * @param contentPadding Padding applied between the button container and its content.
 * @param content Content displayed inside the button.
 *
 * Example:
 * ```
 * UiFilledTonalButton(
 *     onClick = {
 *         addToCart()
 *     }
 * ) {
 *     Text("Add to cart")
 * }
 * ```
 */
@Composable
fun UiFilledTonalButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    shape: Shape = UiButtonDefaults.FilledTonalShape,
    colors: ButtonColors = UiButtonDefaults.filledTonalColors(),
    elevation: ButtonElevation? = UiButtonDefaults.filledTonalElevation(),
    border: BorderStroke? = null,
    contentPadding: PaddingValues = UiButtonDefaults.ContentPadding,
    content: @Composable RowScope.() -> Unit
) {
    FilledTonalButton(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        shape = shape,
        colors = colors,
        elevation = elevation,
        border = border,
        contentPadding = contentPadding,
        content = content
    )
}

/**
 * An elevated button for actions that benefit from additional visual
 * separation from the surrounding surface.
 *
 * @param onClick Called when the user clicks the button.
 * @param modifier Modifier applied to the button.
 * @param enabled Controls whether the button can be interacted with.
 * @param shape Shape applied to the button container.
 * @param colors Colors used by the button for its enabled and disabled states.
 * @param elevation Elevation applied to the button according to its state.
 * @param border Optional border drawn around the button.
 * @param contentPadding Padding applied between the button container and its content.
 * @param content Content displayed inside the button.
 *
 * Example:
 * ```
 * UiElevatedButton(
 *     onClick = {
 *         continueProcess()
 *     }
 * ) {
 *     Text("Continue")
 * }
 * ```
 */
@Composable
fun UiElevatedButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    shape: Shape = UiButtonDefaults.ElevatedShape,
    colors: ButtonColors = UiButtonDefaults.elevatedColors(),
    elevation: ButtonElevation? = UiButtonDefaults.elevatedElevation(),
    border: BorderStroke? = null,
    contentPadding: PaddingValues = UiButtonDefaults.ContentPadding,
    content: @Composable RowScope.() -> Unit
) {
    ElevatedButton(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        shape = shape,
        colors = colors,
        elevation = elevation,
        border = border,
        contentPadding = contentPadding,
        content = content
    )
}

/**
 * An outlined button for secondary actions or alternative actions
 * to the primary action.
 *
 * @param onClick Called when the user clicks the button.
 * @param modifier Modifier applied to the button.
 * @param enabled Controls whether the button can be interacted with.
 * @param shape Shape applied to the button container.
 * @param colors Colors used by the button for its enabled and disabled states.
 * @param elevation Elevation applied to the button according to its state.
 * @param border Optional border drawn around the button.
 * @param contentPadding Padding applied between the button container and its content.
 * @param content Content displayed inside the button.
 *
 * Example:
 * ```
 * UiOutlinedButton(
 *     onClick = {
 *         cancel()
 *     }
 * ) {
 *     Text("Cancel")
 * }
 * ```
 */
@Composable
fun UiOutlinedButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    shape: Shape = UiButtonDefaults.OutlinedShape,
    colors: ButtonColors = UiButtonDefaults.outlinedColors(),
    elevation: ButtonElevation? = null,
    border: BorderStroke? = UiButtonDefaults.outlinedBorder(enabled),
    contentPadding: PaddingValues = UiButtonDefaults.ContentPadding,
    content: @Composable RowScope.() -> Unit
) {
    OutlinedButton(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        shape = shape,
        colors = colors,
        elevation = elevation,
        border = border,
        contentPadding = contentPadding,
        content = content
    )
}

/**
 * A text button for low-emphasis actions.
 *
 * Use [UiTextButton] for secondary actions or actions that should receive
 * less visual emphasis than filled or outlined buttons.
 *
 * @param onClick Called when the user clicks the button.
 * @param modifier Modifier applied to the button.
 * @param enabled Controls whether the button can be interacted with.
 * @param shape Shape applied to the button container.
 * @param colors Colors used by the button for its enabled and disabled states.
 * @param elevation Optional elevation applied to the button.
 * @param border Optional border drawn around the button.
 * @param contentPadding Padding applied between the button container and its content.
 * @param content Content displayed inside the button.
 *
 * Example:
 * ```
 * UiTextButton(
 *     onClick = {
 *         learnMore()
 *     }
 * ) {
 *     Text("Learn more")
 * }
 * ```
 */
@Composable
fun UiTextButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    shape: Shape = UiButtonDefaults.TextShape,
    colors: ButtonColors = UiButtonDefaults.textColors(),
    elevation: ButtonElevation? = null,
    border: BorderStroke? = null,
    contentPadding: PaddingValues = UiButtonDefaults.ContentPadding,
    content: @Composable RowScope.() -> Unit
) {
    TextButton(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        shape = shape,
        colors = colors,
        elevation = elevation,
        border = border,
        contentPadding = contentPadding,
        content = content
    )
}