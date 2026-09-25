package io.github.ordonovus.component.textfield

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.InputTransformation
import androidx.compose.foundation.text.input.KeyboardActionHandler
import androidx.compose.foundation.text.input.OutputTransformation
import androidx.compose.foundation.text.input.TextFieldLineLimits
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.maxLength
import androidx.compose.foundation.text.input.then
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TextFieldLabelScope
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp

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
 * The focused and unfocused indicator thicknesses can be customized
 * independently. Their default values are provided by [UiTextFieldDefaults].
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
 * @param label Optional label displayed by the field.
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
 * @param focusedIndicatorThickness Indicator thickness used while the field
 * is focused.
 * @param unfocusedIndicatorThickness Indicator thickness used while the field
 * is not focused.
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
    lineLimits: TextFieldLineLimits = TextFieldLineLimits.SingleLine,
    shape: Shape = UiTextFieldDefaults.Shape,
    colors: TextFieldColors = UiTextFieldDefaults.colors(readOnly = readOnly),
    focusedIndicatorThickness: Dp = UiTextFieldDefaults.FocusedIndicatorThickness,
    unfocusedIndicatorThickness: Dp = UiTextFieldDefaults.UnfocusedIndicatorThickness
) {
    val interactionSource = remember { MutableInteractionSource() }

    val finalInputTransformation = createInputTransformation(
        maxLength = maxLength,
        inputTransformation = inputTransformation
    )

    val finalSupportingText = supportingContent(
        state = state,
        maxLength = maxLength,
        showCharacterCount = showCharacterCount,
        isError = isError,
        errorText = errorText,
        supportingText = supportingText
    )

    val textColor = when {
        !enabled -> UiTextFieldDefaults.DisabledTextColor
        readOnly -> UiTextFieldDefaults.ReadOnlyTextColor
        else -> UiTextFieldDefaults.TextColor
    }

    BasicTextField(
        state = state,
        modifier = modifier,
        enabled = enabled,
        readOnly = readOnly,
        inputTransformation = finalInputTransformation,
        textStyle = LocalTextStyle.current.copy(
            color = textColor
        ),
        keyboardOptions = keyboardOptions,
        onKeyboardAction = onKeyboardAction,
        lineLimits = lineLimits,
        interactionSource = interactionSource,
        outputTransformation = outputTransformation,
        decorator = TextFieldDefaults.decorator(
            state = state,
            enabled = enabled,
            lineLimits = lineLimits,
            outputTransformation = outputTransformation,
            interactionSource = interactionSource,
            label = label,
            placeholder = placeholder,
            leadingIcon = leadingIcon,
            trailingIcon = trailingIcon,
            prefix = prefix,
            suffix = suffix,
            supportingText = finalSupportingText,
            isError = isError,
            colors = colors,
            container = {
                TextFieldDefaults.Container(
                    enabled = enabled,
                    isError = isError,
                    interactionSource = interactionSource,
                    colors = colors,
                    shape = shape,
                    focusedIndicatorLineThickness = focusedIndicatorThickness,
                    unfocusedIndicatorLineThickness = unfocusedIndicatorThickness
                )
            }
        )
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
 * The focused and unfocused border thicknesses can be customized
 * independently. Their default values are provided by [UiTextFieldDefaults].
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
 * @param label Optional label displayed by the field.
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
 * @param focusedBorderThickness Border thickness used while the field is
 * focused.
 * @param unfocusedBorderThickness Border thickness used while the field is not
 * focused.
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
 *     maxLength = 250,
 *     showCharacterCount = true
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
    lineLimits: TextFieldLineLimits = TextFieldLineLimits.SingleLine,
    shape: Shape = UiTextFieldDefaults.OutlinedShape,
    colors: TextFieldColors = UiTextFieldDefaults.outlinedColors(readOnly = readOnly),
    focusedBorderThickness: Dp = UiTextFieldDefaults.FocusedBorderThickness,
    unfocusedBorderThickness: Dp = UiTextFieldDefaults.UnfocusedBorderThickness
) {
    val interactionSource = remember { MutableInteractionSource() }

    val finalInputTransformation = createInputTransformation(
        maxLength = maxLength,
        inputTransformation = inputTransformation
    )

    val finalSupportingText = supportingContent(
        state = state,
        maxLength = maxLength,
        showCharacterCount = showCharacterCount,
        isError = isError,
        errorText = errorText,
        supportingText = supportingText
    )

    val textColor = when {
        !enabled -> UiTextFieldDefaults.DisabledTextColor
        readOnly -> UiTextFieldDefaults.ReadOnlyTextColor
        else -> UiTextFieldDefaults.TextColor
    }

    BasicTextField(
        state = state,
        modifier = modifier,
        enabled = enabled,
        readOnly = readOnly,
        inputTransformation = finalInputTransformation,
        textStyle = LocalTextStyle.current.copy(
            color = textColor
        ),
        keyboardOptions = keyboardOptions,
        onKeyboardAction = onKeyboardAction,
        lineLimits = lineLimits,
        interactionSource = interactionSource,
        outputTransformation = outputTransformation,
        decorator = OutlinedTextFieldDefaults.decorator(
            state = state,
            enabled = enabled,
            lineLimits = lineLimits,
            outputTransformation = outputTransformation,
            interactionSource = interactionSource,
            label = label,
            placeholder = placeholder,
            leadingIcon = leadingIcon,
            trailingIcon = trailingIcon,
            prefix = prefix,
            suffix = suffix,
            supportingText = finalSupportingText,
            isError = isError,
            colors = colors,
            container = {
                OutlinedTextFieldDefaults.Container(
                    enabled = enabled,
                    isError = isError,
                    interactionSource = interactionSource,
                    colors = colors,
                    shape = shape,
                    focusedBorderThickness = focusedBorderThickness,
                    unfocusedBorderThickness = unfocusedBorderThickness
                )
            }
        )
    )
}

/**
 * Creates the input transformation used by text field components.
 *
 * When a maximum length is provided, the length restriction is applied before
 * the optional consumer-provided transformation.
 *
 * @param maxLength Maximum number of accepted characters, or `null` when no
 * UI Kit length restriction should be applied.
 * @param inputTransformation Optional transformation supplied by the consumer.
 *
 * @return The combined input transformation, or `null` when no transformation
 * is required.
 */
private fun createInputTransformation(
    maxLength: Int?,
    inputTransformation: InputTransformation?
): InputTransformation? {
    val lengthTransformation = maxLength?.let {
        InputTransformation.maxLength(it)
    }

    return when {
        lengthTransformation != null && inputTransformation != null ->
            lengthTransformation.then(inputTransformation)

        lengthTransformation != null ->
            lengthTransformation

        else ->
            inputTransformation
    }
}

/**
 * Creates the supporting content displayed below a text field.
 *
 * The supporting message is aligned to the start while the character counter,
 * when enabled, is aligned to the end. Error text takes precedence over the
 * regular supporting content.
 *
 * @param state Current text field state used to calculate the character count.
 * @param maxLength Maximum number of characters allowed.
 * @param showCharacterCount Whether the character counter should be displayed.
 * @param isError Whether the text field is currently in an error state.
 * @param errorText Error message displayed when the field is in an error state.
 * @param supportingText Optional supporting content displayed below the field.
 *
 * @return Supporting content for the text field, or `null` when no content
 * needs to be displayed.
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
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Box(
                modifier = Modifier.weight(1f)
            ) {
                when {
                    shouldShowError -> Text(text = errorText)
                    supportingText != null -> supportingText()
                }
            }

            if (shouldShowCounter) {
                Text(text = "${state.text.length} / $maxLength")
            }
        }
    }
}