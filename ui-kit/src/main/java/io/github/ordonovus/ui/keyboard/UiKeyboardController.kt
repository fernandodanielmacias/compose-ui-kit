package io.github.ordonovus.ui.keyboard

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController

/**
 * Provides keyboard and focus management actions for Compose UI.
 *
 * The controller allows consumers to dismiss the software keyboard
 * independently or clear the current focus when an interaction
 * should end text input.
 */
@Stable
class UiKeyboardController internal constructor(
    private val hideKeyboardAction: () -> Unit,
    private val clearFocusAction: () -> Unit
) {

    /**
     * Requests the software keyboard to hide.
     *
     * The currently focused component retains focus.
     */
    fun hide() {
        hideKeyboardAction()
    }

    /**
     * Clears the current focus and requests the software keyboard
     * to hide.
     *
     * This is useful when navigating between screens, opening
     * navigation drawers, or executing scroll actions.
     */
    fun dismiss() {
        clearFocusAction()
        hideKeyboardAction()
    }
}

/**
 * Remembers a keyboard controller backed by the current Compose
 * focus manager and software keyboard controller.
 *
 * @return A controller for managing keyboard visibility and focus.
 */
@Composable
fun rememberUiKeyboardController(): UiKeyboardController {
    val focusManager = LocalFocusManager.current
    val keyboardController = LocalSoftwareKeyboardController.current

    return remember(focusManager, keyboardController) {
        UiKeyboardController(
            hideKeyboardAction = {
                keyboardController?.hide()
            },
            clearFocusAction = {
                focusManager.clearFocus()
            }
        )
    }
}