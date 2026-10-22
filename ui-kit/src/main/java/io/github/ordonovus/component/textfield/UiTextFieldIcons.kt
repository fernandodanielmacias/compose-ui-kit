package io.github.ordonovus.component.textfield

import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import io.github.ordonovus.R

/**
 * Displays the default error indicator used by text field components.
 *
 * The indicator uses the error color from the active icon palette.
 * It is decorative and should be accompanied by an accessible error
 * message when additional validation information is available.
 *
 * @param iconColors Colors applied to the indicator.
 */
@Composable
internal fun UiTextFieldErrorIcon(
    iconColors: UiTextFieldIconColors = UiTextFieldIconDefaults.current
) {
    Icon(
        painter = painterResource(R.drawable.ic_error),
        contentDescription = null,
        tint = iconColors.error
    )
}

/**
 * Displays the default leading lock icon for secure text fields.
 *
 * The icon uses the configured action color when enabled and the
 * disabled color otherwise.
 *
 * @param enabled Whether the associated text field is enabled.
 * @param iconColors Colors applied to the icon.
 */
@Composable
internal fun UiPasswordLeadingIcon(
    enabled: Boolean = true,
    iconColors: UiTextFieldIconColors = UiTextFieldIconDefaults.current
) {
    Icon(
        painter = painterResource(R.drawable.ic_lock),
        contentDescription = null,
        tint = if (enabled) {
            iconColors.action
        } else {
            iconColors.disabled
        }
    )
}