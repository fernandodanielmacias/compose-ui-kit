package io.github.ordonovus.component.fab

import androidx.annotation.DrawableRes
import androidx.compose.foundation.lazy.LazyListState
import io.github.ordonovus.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

/**
 * Represents the navigation actions available at a lazy list position.
 */
internal enum class UiScrollNavigationDirection {
    Top,
    Bottom,
    Both,
    None
}

/**
 * Resolves the navigation direction from the available scroll actions.
 *
 * @param canScrollBackward Whether the list can scroll toward its beginning.
 * @param canScrollForward Whether the list can scroll toward its end.
 * @return The direction associated with the current list position.
 */
internal fun resolveScrollNavigationDirection(
    canScrollBackward: Boolean,
    canScrollForward: Boolean
): UiScrollNavigationDirection = when {
    canScrollBackward && canScrollForward -> UiScrollNavigationDirection.Both
    canScrollBackward -> UiScrollNavigationDirection.Top
    canScrollForward -> UiScrollNavigationDirection.Bottom
    else -> UiScrollNavigationDirection.None
}

/**
 * Returns the drawable associated with this navigation direction.
 *
 * @return Drawable resource representing the available action.
 */
@DrawableRes
internal fun UiScrollNavigationDirection.iconRes(): Int = when (this) {
    UiScrollNavigationDirection.Top -> R.drawable.ic_arrow_up
    UiScrollNavigationDirection.Bottom -> R.drawable.ic_arrow_down
    UiScrollNavigationDirection.Both,
    UiScrollNavigationDirection.None -> R.drawable.ic_arrow_down_up
}

/**
 * Returns the accessibility description for this navigation direction.
 *
 * @param topDescription Description for scrolling to the beginning.
 * @param bottomDescription Description for scrolling to the end.
 * @param navigationDescription Description for opening navigation options.
 * @return Accessibility description of the available action.
 */
internal fun UiScrollNavigationDirection.description(
    topDescription: String,
    bottomDescription: String,
    navigationDescription: String
): String = when (this) {
    UiScrollNavigationDirection.Top -> topDescription
    UiScrollNavigationDirection.Bottom -> bottomDescription
    UiScrollNavigationDirection.Both,
    UiScrollNavigationDirection.None -> navigationDescription
}

/**
 * Launches an animated scroll to the specified lazy list item.
 *
 * The completion callback is invoked when the animation finishes
 * or is canceled.
 *
 * @param listState State of the target lazy list.
 * @param targetIndex Index of the destination item.
 * @param coroutineScope Scope used to launch the animation.
 * @param onFinished Callback invoked after the scroll ends.
 */
internal fun scrollToListItem(
    listState: LazyListState,
    targetIndex: Int,
    coroutineScope: CoroutineScope,
    onFinished: () -> Unit
) {
    coroutineScope.launch {
        try {
            listState.animateScrollToItem(targetIndex)
        } finally {
            onFinished()
        }
    }
}

/**
 * Resolves the direction displayed by the floating action button.
 *
 * The actual direction takes precedence at either end of the list.
 * At intermediate positions, the direction can remain frozen during
 * a programmatic scroll.
 *
 * @param actualDirection Direction supported by the current list position.
 * @param frozenDirection Direction preserved during programmatic scrolling.
 * @return Direction that should be displayed by the button.
 */
internal fun resolveDisplayedScrollDirection(
    actualDirection: UiScrollNavigationDirection,
    frozenDirection: UiScrollNavigationDirection?
): UiScrollNavigationDirection = when (actualDirection) {
    UiScrollNavigationDirection.Top -> UiScrollNavigationDirection.Top
    UiScrollNavigationDirection.Bottom -> UiScrollNavigationDirection.Bottom
    else -> frozenDirection ?: actualDirection
}

/**
 * Returns the index of the last item in a lazy list.
 *
 * This function should be called from an event handler rather than
 * directly during composition because layout information changes frequently.
 *
 * @param listState State of the target lazy list.
 * @return Index of the last item, or -1 if the list is empty.
 */
internal fun lastScrollItemIndex(
    listState: LazyListState
): Int = listState.layoutInfo.totalItemsCount - 1

/**
 * Handles the primary navigation action for the current direction.
 *
 * At either end of the list, the button requests a scroll toward
 * the opposite end. At intermediate positions, it opens the
 * navigation menu.
 *
 * @param direction Currently displayed navigation direction.
 * @param listState State of the lazy list.
 * @param onScrollTo Callback requesting navigation to an item.
 * @param onShowMenu Callback requesting the navigation menu.
 */
internal fun handleScrollNavigationClick(
    direction: UiScrollNavigationDirection,
    listState: LazyListState,
    onScrollTo: (Int) -> Unit,
    onShowMenu: () -> Unit
) {
    when (direction) {
        UiScrollNavigationDirection.Top -> {
            onScrollTo(0)
        }

        UiScrollNavigationDirection.Bottom -> {
            val lastIndex = lastScrollItemIndex(listState)

            if (lastIndex >= 0) {
                onScrollTo(lastIndex)
            }
        }

        UiScrollNavigationDirection.Both -> {
            onShowMenu()
        }

        UiScrollNavigationDirection.None -> Unit
    }
}