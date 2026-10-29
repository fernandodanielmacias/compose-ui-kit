package io.github.ordonovus.component.fab

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.FloatingActionButtonElevation
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import io.github.ordonovus.R
import io.github.ordonovus.ui.keyboard.rememberUiKeyboardController

/**
 * Displays a contextual floating action button for navigating
 * to the beginning or end of a lazy list.
 *
 * At the beginning of the list, the button scrolls to the last item.
 * At the end of the list, the button scrolls to the first item.
 *
 * At intermediate positions, the button opens a menu that allows
 * the user to choose a navigation direction.
 *
 * The button is hidden when the list cannot scroll.
 *
 * During programmatic scrolling, the displayed icon is preserved
 * at intermediate positions. At either end of the list, it reflects
 * the available navigation action.
 *
 * @param listState State of the lazy list being controlled.
 * @param scrollToTopDescription Accessibility description for
 * scrolling to the beginning of the list.
 * @param scrollToBottomDescription Accessibility description for
 * scrolling to the end of the list.
 * @param navigationDescription Accessibility description for
 * opening the navigation menu.
 * @param modifier Modifier applied to the component.
 * @param dismissKeyboardOnClick Whether to dismiss the software keyboard
 * and clear focus before executing a scroll action.
 * @param shape Shape of the floating action button.
 * @param containerColor Background color of the button.
 * @param contentColor Color of the button content.
 * @param elevation Elevation configuration for interaction states.
 */
@Composable
fun UiScrollNavigationFab(
    listState: LazyListState,
    scrollToTopDescription: String,
    scrollToBottomDescription: String,
    navigationDescription: String,
    modifier: Modifier = Modifier,
    dismissKeyboardOnClick: Boolean = false,
    shape: Shape = UiFloatingActionButtonDefaults.Shape,
    containerColor: Color = UiFloatingActionButtonDefaults.ContainerColor,
    contentColor: Color = contentColorFor(containerColor),
    elevation: FloatingActionButtonElevation =
        UiFloatingActionButtonDefaults.elevation()
) {
    val coroutineScope = rememberCoroutineScope()
    val keyboardController = rememberUiKeyboardController()

    var menuExpanded by remember(listState) {
        mutableStateOf(false)
    }

    var frozenDirection by remember(listState) {
        mutableStateOf<UiScrollNavigationDirection?>(null)
    }

    val actualDirection = resolveScrollNavigationDirection(
        canScrollBackward = listState.canScrollBackward,
        canScrollForward = listState.canScrollForward
    )

    val displayedDirection = resolveDisplayedScrollDirection(
        actualDirection = actualDirection,
        frozenDirection = frozenDirection
    )

    val isProgrammaticScroll = frozenDirection != null

    fun scrollTo(index: Int) {
        if (isProgrammaticScroll) return

        menuExpanded = false
        frozenDirection = displayedDirection

        if (dismissKeyboardOnClick) {
            keyboardController.dismiss()
        }

        scrollToListItem(
            listState = listState,
            targetIndex = index,
            coroutineScope = coroutineScope,
            onFinished = {
                frozenDirection = null
            }
        )
    }

    AnimatedVisibility(
        visible = actualDirection != UiScrollNavigationDirection.None,
        modifier = modifier,
        enter = fadeIn() + scaleIn(),
        exit = fadeOut() + scaleOut()
    ) {
        Box {
            UiFloatingActionButton(
                onClick = {
                    if (!isProgrammaticScroll) {
                        handleScrollNavigationClick(
                            direction = displayedDirection,
                            listState = listState,
                            onScrollTo = ::scrollTo,
                            onShowMenu = {
                                menuExpanded = true
                            }
                        )
                    }
                },
                modifier = Modifier.semantics {
                    contentDescription = displayedDirection.description(
                        topDescription = scrollToTopDescription,
                        bottomDescription = scrollToBottomDescription,
                        navigationDescription = navigationDescription
                    )
                },
                shape = shape,
                containerColor = containerColor,
                contentColor = contentColor,
                elevation = elevation
            ) {
                Icon(
                    painter = painterResource(
                        displayedDirection.iconRes()
                    ),
                    contentDescription = null
                )
            }

            DropdownMenu(
                expanded = menuExpanded &&
                        !isProgrammaticScroll &&
                        actualDirection == UiScrollNavigationDirection.Both,
                onDismissRequest = {
                    menuExpanded = false
                }
            ) {
                DropdownMenuItem(
                    text = {
                        Text(scrollToTopDescription)
                    },
                    leadingIcon = {
                        Icon(
                            painter = painterResource(R.drawable.ic_arrow_up),
                            contentDescription = null
                        )
                    },
                    onClick = {
                        scrollTo(0)
                    }
                )

                DropdownMenuItem(
                    text = {
                        Text(scrollToBottomDescription)
                    },
                    leadingIcon = {
                        Icon(
                            painter = painterResource(R.drawable.ic_arrow_down),
                            contentDescription = null
                        )
                    },
                    onClick = {
                        val lastIndex = lastScrollItemIndex(listState)

                        if (lastIndex >= 0) {
                            scrollTo(lastIndex)
                        }
                    }
                )
            }
        }
    }
}