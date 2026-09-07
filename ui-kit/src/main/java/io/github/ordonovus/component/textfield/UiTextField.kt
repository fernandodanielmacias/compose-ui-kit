package io.github.ordonovus.component.textfield

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.InputTransformation
import androidx.compose.foundation.text.input.KeyboardActionHandler
import androidx.compose.foundation.text.input.OutputTransformation
import androidx.compose.foundation.text.input.TextFieldLineLimits
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.maxLength
import androidx.compose.foundation.text.input.then
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldLabelScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape

/**
 * A filled Material 3 text field configured for the Compose UI Kit design system.
 *
 * This component uses the state-based text field API through [TextFieldState].
 * It supports enabled, disabled, read-only, and error states while inheriting
 * its default appearance from the active Compose UI Kit theme.
 *
 * A maximum input length can be enforced through [maxLength]. When
 * [showCharacterCount] is enabled and a maximum length is defined, the
 * component automatically displays the current character count.
 *
 * Custom [InputTransformation] and [OutputTransformation] instances can be
 * provided for advanced input filtering and visual transformations.
 *
 * @param state State that owns and manages the editable text.
 * @param modifier Modifier applied to the text field.
 * @param enabled Whether the field is enabled for user interaction.
 * When `false`, the field uses its disabled visual state.
 * @param readOnly Whether the field prevents text modification while remaining
 * available for interactions such as text selection and copying.
 * @param isError Whether the field should display its error visual state.
 * @param errorText Optional error message displayed as supporting content when
 * [isError] is `true`. When present, it takes precedence over [supportingText].
 * @param label Optional label displayed by the field. The content is provided
 * within a [TextFieldLabelScope], allowing it to participate in the Material 3
 * label behavior and animations.
 * @param placeholder Optional content displayed when the field is empty.
 * @param supportingText Optional supporting content displayed below the field.
 * It is replaced by [errorText] while a non-empty error message is active.
 * @param leadingIcon Optional icon or content displayed at the beginning of
 * the field.
 * @param trailingIcon Optional icon or content displayed at the end of the
 * field.
 * @param prefix Optional content displayed before the editable text.
 * @param suffix Optional content displayed after the editable text.
 * @param maxLength Optional maximum number of characters accepted by the
 * field. When `null`, no UI Kit maximum length restriction is applied.
 * @param showCharacterCount Whether to display the current character count.
 * The counter is displayed only when [maxLength] is defined.
 * @param keyboardOptions Configuration used by the software keyboard.
 * @param onKeyboardAction Optional handler invoked when the user performs the
 * configured IME action.
 * @param inputTransformation Optional transformation applied to user input.
 * When [maxLength] is defined, the UI Kit length restriction is combined with
 * this transformation.
 * @param outputTransformation Optional transformation used to change how the
 * text is visually presented without modifying the underlying [state].
 * @param lineLimits Defines the minimum and maximum line behavior of the field.
 * @param shape Shape used by the text field container.
 * @param colors Colors used by the text field for its different visual states.
 *
 * Example:
 * ```
 * val state = rememberTextFieldState()
 *
 * UiTextField(
 *     state = state,
 *     label = {
 *         UiText("Username")
 *     },
 *     placeholder = {
 *         UiText("Enter username")
 *     },
 *     maxLength = 30,
 *     showCharacterCount = true
 * )
 * ```
 *
 * Example with an error:
 * ```
 * UiTextField(
 *     state = state,
 *     label = {
 *         UiText("Username")
 *     },
 *     isError = true,
 *     errorText = "Username is required"
 * )
 * ```
 */
@Composable
fun UiTextField(
    state: TextFieldState,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    isError: Boolean = false,
    errorText: String? = null,
    label: (@Composable TextFieldLabelScope.() -> Unit)? = null,
    placeholder: (@Composable () -> Unit)? = null,
    supportingText: (@Composable () -> Unit)? = null,
    leadingIcon: (@Composable () -> Unit)? = null,
    trailingIcon: (@Composable () -> Unit)? = null,
    prefix: (@Composable () -> Unit)? = null,
    suffix: (@Composable () -> Unit)? = null,
    maxLength: Int? = null,
    showCharacterCount: Boolean = false,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    onKeyboardAction: KeyboardActionHandler? = null,
    inputTransformation: InputTransformation? = null,
    outputTransformation: OutputTransformation? = null,
    lineLimits: TextFieldLineLimits = TextFieldLineLimits.Default,
    shape: Shape = UiTextFieldDefaults.Shape,
    colors: TextFieldColors = UiTextFieldDefaults.colors(readOnly = readOnly)
) {
    val lengthTransformation = maxLength?.let {
        InputTransformation.maxLength(it)
    }

    val finalInputTransformation = when {
        lengthTransformation != null && inputTransformation != null ->
            lengthTransformation.then(inputTransformation)

        lengthTransformation != null ->
            lengthTransformation

        else ->
            inputTransformation
    }

    TextField(
        state = state,
        modifier = modifier,
        enabled = enabled,
        readOnly = readOnly,
        isError = isError,
        label = label,
        placeholder = placeholder,
        supportingText = supportingContent(
            state = state,
            maxLength = maxLength,
            showCharacterCount = showCharacterCount,
            isError = isError,
            errorText = errorText,
            supportingText = supportingText
        ),
        leadingIcon = leadingIcon,
        trailingIcon = trailingIcon,
        prefix = prefix,
        suffix = suffix,
        keyboardOptions = keyboardOptions,
        onKeyboardAction = onKeyboardAction,
        inputTransformation = finalInputTransformation,
        outputTransformation = outputTransformation,
        lineLimits = lineLimits,
        shape = shape,
        colors = colors
    )
}

/**
 * An outlined Material 3 text field configured for the Compose UI Kit
 * design system.
 *
 * This component provides the same state-based input capabilities as
 * [UiTextField] while using the Material 3 outlined visual style.
 *
 * It supports enabled, disabled, read-only, and error states, maximum input
 * length enforcement, an optional character counter, custom input
 * transformations, output transformations, and keyboard actions.
 *
 * @param state State that owns and manages the editable text.
 * @param modifier Modifier applied to the text field.
 * @param enabled Whether the field is enabled for user interaction.
 * When `false`, the field uses its disabled visual state.
 * @param readOnly Whether the field prevents text modification while remaining
 * available for interactions such as text selection and copying.
 * @param isError Whether the field should display its error visual state.
 * @param errorText Optional error message displayed as supporting content when
 * [isError] is `true`. When present, it takes precedence over [supportingText].
 * @param label Optional label displayed by the field. The content is provided
 * within a [TextFieldLabelScope], allowing it to participate in the Material 3
 * label behavior and animations.
 * @param placeholder Optional content displayed when the field is empty.
 * @param supportingText Optional supporting content displayed below the field.
 * It is replaced by [errorText] while a non-empty error message is active.
 * @param leadingIcon Optional icon or content displayed at the beginning of
 * the field.
 * @param trailingIcon Optional icon or content displayed at the end of the
 * field.
 * @param prefix Optional content displayed before the editable text.
 * @param suffix Optional content displayed after the editable text.
 * @param maxLength Optional maximum number of characters accepted by the
 * field. When `null`, no UI Kit maximum length restriction is applied.
 * @param showCharacterCount Whether to display the current character count.
 * The counter is displayed only when [maxLength] is defined.
 * @param keyboardOptions Configuration used by the software keyboard.
 * @param onKeyboardAction Optional handler invoked when the user performs the
 * configured IME action.
 * @param inputTransformation Optional transformation applied to user input.
 * When [maxLength] is defined, the UI Kit length restriction is combined with
 * this transformation.
 * @param outputTransformation Optional transformation used to change how the
 * text is visually presented without modifying the underlying [state].
 * @param lineLimits Defines the minimum and maximum line behavior of the field.
 * @param shape Shape used by the outlined field container.
 * @param colors Colors used by the field for its different visual states.
 *
 * Example:
 * ```
 * val state = rememberTextFieldState()
 *
 * UiOutlinedTextField(
 *     state = state,
 *     label = {
 *         UiText("Description")
 *     },
 *     placeholder = {
 *         UiText("Enter a description")
 *     },
 *     maxLength = 250,
 *     showCharacterCount = true
 * )
 * ```
 *
 * Example with an error:
 * ```
 * UiOutlinedTextField(
 *     state = state,
 *     label = {
 *         UiText("Email")
 *     },
 *     isError = true,
 *     errorText = "Enter a valid email address"
 * )
 * ```
 */
@Composable
fun UiOutlinedTextField(
    state: TextFieldState,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    isError: Boolean = false,
    errorText: String? = null,
    label: (@Composable TextFieldLabelScope.() -> Unit)? = null,
    placeholder: (@Composable () -> Unit)? = null,
    supportingText: (@Composable () -> Unit)? = null,
    leadingIcon: (@Composable () -> Unit)? = null,
    trailingIcon: (@Composable () -> Unit)? = null,
    prefix: (@Composable () -> Unit)? = null,
    suffix: (@Composable () -> Unit)? = null,
    maxLength: Int? = null,
    showCharacterCount: Boolean = false,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    onKeyboardAction: KeyboardActionHandler? = null,
    inputTransformation: InputTransformation? = null,
    outputTransformation: OutputTransformation? = null,
    lineLimits: TextFieldLineLimits = TextFieldLineLimits.Default,
    shape: Shape = UiTextFieldDefaults.OutlinedShape,
    colors: TextFieldColors = UiTextFieldDefaults.outlinedColors(readOnly = readOnly)
) {
    val lengthTransformation = maxLength?.let {
        InputTransformation.maxLength(it)
    }

    val finalInputTransformation = when {
        lengthTransformation != null && inputTransformation != null ->
            lengthTransformation.then(inputTransformation)

        lengthTransformation != null ->
            lengthTransformation

        else ->
            inputTransformation
    }

    OutlinedTextField(
        state = state,
        modifier = modifier,
        enabled = enabled,
        readOnly = readOnly,
        isError = isError,
        label = label,
        placeholder = placeholder,
        supportingText = supportingContent(
            state = state,
            maxLength = maxLength,
            showCharacterCount = showCharacterCount,
            isError = isError,
            errorText = errorText,
            supportingText = supportingText
        ),
        leadingIcon = leadingIcon,
        trailingIcon = trailingIcon,
        prefix = prefix,
        suffix = suffix,
        keyboardOptions = keyboardOptions,
        onKeyboardAction = onKeyboardAction,
        inputTransformation = finalInputTransformation,
        outputTransformation = outputTransformation,
        lineLimits = lineLimits,
        shape = shape,
        colors = colors
    )
}

/**
 * Creates the supporting content used by text field components.
 *
 * Error text takes precedence over custom supporting content. When enabled,
 * the character counter is appended below the field without requiring the
 * consumer to manually track the current text length.
 *
 * @return Supporting content for the text field, or `null` when no content
 * should be displayed.
 */
@Composable
private fun supportingContent(
    state: TextFieldState,
    maxLength: Int?,
    showCharacterCount: Boolean,
    isError: Boolean,
    errorText: String?,
    supportingText: (@Composable () -> Unit)?
): (@Composable () -> Unit)? {
    val shouldShowError = isError && !errorText.isNullOrBlank()
    val shouldShowCounter = showCharacterCount && maxLength != null

    if (!shouldShowError && !shouldShowCounter && supportingText == null) {
        return null
    }

    return {
        when {
            shouldShowError -> {
                Text(errorText)
            }

            supportingText != null -> {
                supportingText()
            }
        }

        if (shouldShowCounter) {
            Text(
                text = "${state.text.length} / $maxLength"
            )
        }
    }
}