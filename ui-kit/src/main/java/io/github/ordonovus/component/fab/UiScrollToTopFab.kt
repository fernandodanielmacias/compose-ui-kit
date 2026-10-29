
package io.github.ordonovus.component.fab

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.material3.FloatingActionButtonElevation
import androidx.compose.material3.Icon
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.derivedStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import io.github.ordonovus.R
import io.github.ordonovus.ui.keyboard.rememberUiKeyboardController
import kotlinx.coroutines.launch

/**
 * Displays a contextual floating action button that scrolls a lazy list
 * back to its first item.
 *
 * The button automatically becomes visible when the list exceeds the
 * configured scroll threshold and hides when the user returns to the top.
 *
 * Visibility changes are animated, and clicking the button performs
 * a smooth scroll to the first item using [LazyListState].
 *
 * Optionally, the button can dismiss the software keyboard and clear
 * the currently focused component before starting the scroll action.
 *
 * This component supports both LazyColumn and LazyRow.
 *
 * @param listState State of the lazy list controlled by the button.
 * @param contentDescription Accessibility description of the scroll action.
 * @param modifier Modifier applied to the button container.
 * @param visibilityThreshold Minimum scroll offset in pixels required to
 * display the button while the first item remains visible.
 * @param dismissKeyboardOnClick Whether to dismiss the software keyboard
 * and clear focus before scrolling to the first item.
 * @param shape Shape of the floating action button.
 * @param containerColor Background color of the floating action button.
 * @param contentColor Color of the button content.
 * @param elevation Elevation configuration for interaction states.
 */
@Composable
fun UiScrollToTopFab(
    listState: LazyListState,
    contentDescription: String,
    modifier: Modifier = Modifier,
    visibilityThreshold: Int = 200,
    dismissKeyboardOnClick: Boolean = false,
    shape: Shape = UiFloatingActionButtonDefaults.Shape,
    containerColor: Color = UiFloatingActionButtonDefaults.ContainerColor,
    contentColor: Color = contentColorFor(containerColor),
    elevation: FloatingActionButtonElevation =
        UiFloatingActionButtonDefaults.elevation()
) {
    val coroutineScope = rememberCoroutineScope()
    val keyboardController = rememberUiKeyboardController()

    val isVisible by remember(listState, visibilityThreshold) {
        derivedStateOf {
            listState.firstVisibleItemIndex > 0 ||
                    listState.firstVisibleItemScrollOffset > visibilityThreshold
        }
    }

    AnimatedVisibility(
        visible = isVisible,
        modifier = modifier,
        enter = fadeIn() + scaleIn(),
        exit = fadeOut() + scaleOut()
    ) {
        UiFloatingActionButton(
            onClick = {
                if (dismissKeyboardOnClick) {
                    keyboardController.dismiss()
                }

                coroutineScope.launch {
                    listState.animateScrollToItem(0)
                }
            },
            modifier = Modifier.semantics {
                this.contentDescription = contentDescription
            },
            shape = shape,
            containerColor = containerColor,
            contentColor = contentColor,
            elevation = elevation
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_arrow_up),
                contentDescription = null
            )
        }
    }
}