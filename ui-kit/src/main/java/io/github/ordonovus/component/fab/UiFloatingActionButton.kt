package io.github.ordonovus.component.fab

import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonElevation
import androidx.compose.material3.LargeFloatingActionButton
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.material3.contentColorFor

/**
 * Displays a standard floating action button for a primary screen action.
 *
 * @param onClick Called when the button is clicked.
 * @param modifier Modifier applied to the button.
 * @param shape Shape of the button container.
 * @param containerColor Background color of the button.
 * @param contentColor Preferred color for the button content.
 * @param elevation Elevation configuration for interaction states.
 * @param content Composable content, typically an icon.
 */
@Composable
fun UiFloatingActionButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    shape: Shape = UiFloatingActionButtonDefaults.Shape,
    containerColor: Color = UiFloatingActionButtonDefaults.ContainerColor,
    contentColor: Color = contentColorFor(containerColor),
    elevation: FloatingActionButtonElevation =
        UiFloatingActionButtonDefaults.elevation(),
    content: @Composable () -> Unit
) {
    FloatingActionButton(
        onClick = onClick,
        modifier = modifier,
        shape = shape,
        containerColor = containerColor,
        contentColor = contentColor,
        elevation = elevation,
        content = content
    )
}

/**
 * Displays a compact floating action button.
 *
 * @param onClick Called when the button is clicked.
 * @param modifier Modifier applied to the button.
 * @param shape Shape of the button container.
 * @param containerColor Background color of the button.
 * @param contentColor Preferred color for the button content.
 * @param elevation Elevation configuration for interaction states.
 * @param content Composable content, typically an icon.
 */
@Composable
fun UiSmallFloatingActionButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    shape: Shape = UiFloatingActionButtonDefaults.SmallShape,
    containerColor: Color = UiFloatingActionButtonDefaults.ContainerColor,
    contentColor: Color = contentColorFor(containerColor),
    elevation: FloatingActionButtonElevation =
        UiFloatingActionButtonDefaults.elevation(),
    content: @Composable () -> Unit
) {
    SmallFloatingActionButton(
        onClick = onClick,
        modifier = modifier,
        shape = shape,
        containerColor = containerColor,
        contentColor = contentColor,
        elevation = elevation,
        content = content
    )
}

/**
 * Displays a large floating action button for a prominent action.
 *
 * @param onClick Called when the button is clicked.
 * @param modifier Modifier applied to the button.
 * @param shape Shape of the button container.
 * @param containerColor Background color of the button.
 * @param contentColor Preferred color for the button content.
 * @param elevation Elevation configuration for interaction states.
 * @param content Composable content, typically an icon.
 */
@Composable
fun UiLargeFloatingActionButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    shape: Shape = UiFloatingActionButtonDefaults.LargeShape,
    containerColor: Color = UiFloatingActionButtonDefaults.ContainerColor,
    contentColor: Color = contentColorFor(containerColor),
    elevation: FloatingActionButtonElevation =
        UiFloatingActionButtonDefaults.elevation(),
    content: @Composable () -> Unit
) {
    LargeFloatingActionButton(
        onClick = onClick,
        modifier = modifier,
        shape = shape,
        containerColor = containerColor,
        contentColor = contentColor,
        elevation = elevation,
        content = content
    )
}

/**
 * Displays an extended floating action button with an icon and text.
 *
 * The button can collapse to its icon while preserving its click action.
 *
 * @param onClick Called when the button is clicked.
 * @param text Text content displayed when expanded.
 * @param icon Icon content displayed in both expanded and collapsed states.
 * @param modifier Modifier applied to the button.
 * @param expanded Whether the text label is visible.
 * @param shape Shape of the button container.
 * @param containerColor Background color of the button.
 * @param contentColor Preferred color for the button content.
 * @param elevation Elevation configuration for interaction states.
 */
@Composable
fun UiExtendedFloatingActionButton(
    onClick: () -> Unit,
    text: @Composable () -> Unit,
    icon: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    expanded: Boolean = true,
    shape: Shape = UiFloatingActionButtonDefaults.ExtendedShape,
    containerColor: Color = UiFloatingActionButtonDefaults.ContainerColor,
    contentColor: Color = contentColorFor(containerColor),
    elevation: FloatingActionButtonElevation =
        UiFloatingActionButtonDefaults.elevation()
) {
    ExtendedFloatingActionButton(
        text = text,
        icon = icon,
        onClick = onClick,
        modifier = modifier,
        expanded = expanded,
        shape = shape,
        containerColor = containerColor,
        contentColor = contentColor,
        elevation = elevation
    )
}