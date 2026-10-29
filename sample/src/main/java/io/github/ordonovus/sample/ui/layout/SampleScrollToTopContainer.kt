package io.github.ordonovus.sample.ui.layout

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import io.github.ordonovus.component.fab.UiScrollNavigationFab
import io.github.ordonovus.component.fab.UiScrollToTopFab
import io.github.ordonovus.sample.R
import io.github.ordonovus.theme.UiDimensMedium

/**
 * Defines the available scroll navigation behaviors for sample screens.
 */
enum class SampleScrollFabMode {
    ScrollToTop,
    Bidirectional
}

/**
 * Provides a shared contextual scroll navigation layout for lazy list samples.
 *
 * The container overlays a floating action button on the supplied content
 * and keeps it above the software keyboard when keyboard insets are available.
 *
 * @param listState State shared by the lazy list and its navigation button.
 * @param modifier Modifier applied to the container.
 * @param mode Navigation behavior displayed by the floating action button.
 * @param content Composable content containing the lazy list.
 */
@Composable
fun SampleScrollToTopContainer(
    listState: LazyListState,
    modifier: Modifier = Modifier,
    mode: SampleScrollFabMode = SampleScrollFabMode.ScrollToTop,
    content: @Composable () -> Unit
) {
    Box(
        modifier = modifier.fillMaxSize()
    ) {
        content()

        val fabModifier = Modifier
            .align(Alignment.BottomEnd)
            .imePadding()
            .padding(UiDimensMedium)

        when (mode) {
            SampleScrollFabMode.ScrollToTop -> {
                UiScrollToTopFab(
                    listState = listState,
                    contentDescription = stringResource(
                        R.string.sample_fab_scroll_to_top
                    ),
                    dismissKeyboardOnClick = true,
                    modifier = fabModifier
                )
            }

            SampleScrollFabMode.Bidirectional -> {
                UiScrollNavigationFab(
                    listState = listState,
                    scrollToTopDescription = stringResource(
                        R.string.sample_fab_scroll_to_top
                    ),
                    scrollToBottomDescription = stringResource(
                        R.string.sample_fab_scroll_to_bottom
                    ),
                    navigationDescription = stringResource(
                        R.string.sample_fab_scroll_navigation
                    ),
                    dismissKeyboardOnClick = true,
                    modifier = fabModifier
                )
            }
        }
    }
}