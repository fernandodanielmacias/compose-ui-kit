package io.github.ordonovus.component.text

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.TextUnit

/**
 * Semantic typography variants provided by Compose UI Kit.
 *
 * Each variant maps to a Material 3 typography role from the active
 * [MaterialTheme], allowing applications to customize typography
 * globally without changing individual text components.
 */
enum class UiTextVariant {

    /**
     * Large display text intended for highly prominent content.
     */
    Display,

    /**
     * Primary heading used for screens or major sections.
     */
    Heading,

    /**
     * Title used for sections, cards, dialogs, and similar content.
     */
    Title,

    /**
     * Primary body text.
     */
    Body,

    /**
     * Secondary body text used for supporting or less prominent content.
     */
    BodySecondary,

    /**
     * Compact text typically used for labels and controls.
     */
    Label
}

/**
 * Resolves the typography style associated with this semantic variant.
 *
 * Styles are obtained from the active Material theme, allowing
 * applications to customize typography globally without changing
 * individual text components.
 *
 * @return The [TextStyle] defined by the active Material theme.
 */
@Composable
private fun UiTextVariant.toTextStyle(): TextStyle {
    val typography = MaterialTheme.typography

    return when (this) {
        UiTextVariant.Display -> typography.displayLarge
        UiTextVariant.Heading -> typography.headlineLarge
        UiTextVariant.Title -> typography.titleLarge
        UiTextVariant.Body -> typography.bodyLarge
        UiTextVariant.BodySecondary -> typography.bodyMedium
        UiTextVariant.Label -> typography.labelLarge
    }
}


/**
 * Displays text using the semantic typography system provided by Compose UI Kit.
 *
 * [UiText] should be preferred when displaying standard text content so
 * typography remains consistent across an application. The [variant] defines
 * the semantic role of the text and resolves its style from the active
 * [MaterialTheme.typography]. Other parameters allow individual overrides.
 *
 * By default, the text uses the current content color provided by the active
 * [io.github.ordonovus.theme.UiKitTheme], allowing it to adapt automatically
 * to light and dark themes.
 *
 * @param text Text to display.
 * @param modifier Modifier applied to the text.
 * @param variant Semantic typography variant used by default.
 * @param color Text color. When unspecified, the current content color is used.
 * @param fontSize Optional font size override.
 * @param fontStyle Optional font style override.
 * @param fontWeight Optional font weight override.
 * @param fontFamily Optional font family override.
 * @param letterSpacing Optional letter spacing override.
 * @param textDecoration Optional text decoration.
 * @param textAlign Horizontal alignment of the text.
 * @param lineHeight Optional line height override.
 * @param overflow Behavior when the text exceeds its available space.
 * @param softWrap Whether text should wrap at soft line breaks.
 * @param maxLines Maximum number of lines allowed.
 * @param minLines Minimum number of lines reserved for the text.
 * @param style Optional complete style override. When provided, it replaces
 * the typography style resolved from the active theme for [variant].
 *
 * Example:
 * ```
 * UiText(
 *     text = "Account information",
 *     variant = UiTextVariant.Title
 * )
 * ```
 *
 * Example with an override:
 * ```
 * UiText(
 *     text = "Required field",
 *     variant = UiTextVariant.BodySecondary,
 *     color = MaterialTheme.colorScheme.error,
 *     fontWeight = FontWeight.Medium
 * )
 * ```
 */
@Composable
fun UiText(
    text: String,
    modifier: Modifier = Modifier,
    variant: UiTextVariant = UiTextVariant.Body,
    color: Color = Color.Unspecified,
    fontSize: TextUnit = TextUnit.Unspecified,
    fontStyle: FontStyle? = null,
    fontWeight: FontWeight? = null,
    fontFamily: FontFamily? = null,
    letterSpacing: TextUnit = TextUnit.Unspecified,
    textDecoration: TextDecoration? = null,
    textAlign: TextAlign? = null,
    lineHeight: TextUnit = TextUnit.Unspecified,
    overflow: TextOverflow = TextOverflow.Clip,
    softWrap: Boolean = true,
    maxLines: Int = Int.MAX_VALUE,
    minLines: Int = 1,
    style: TextStyle? = null
) {
    Text(
        text = text,
        modifier = modifier,
        color = color,
        fontSize = fontSize,
        fontStyle = fontStyle,
        fontWeight = fontWeight,
        fontFamily = fontFamily,
        letterSpacing = letterSpacing,
        textDecoration = textDecoration,
        textAlign = textAlign,
        lineHeight = lineHeight,
        overflow = overflow,
        softWrap = softWrap,
        maxLines = maxLines,
        minLines = minLines,
        style = style ?: variant.toTextStyle()
    )
}

/**
 * Displays styled or annotated text using the semantic typography system
 * provided by Compose UI Kit.
 *
 * This overload is useful when different sections of the same text require
 * different styles, annotations, or formatting.
 *
 * @param text Annotated text to display.
 * @param modifier Modifier applied to the text.
 * @param variant Semantic typography variant resolved from the active
 * [MaterialTheme.typography].
 * @param color Text color. When unspecified, the current content color is used.
 * @param textAlign Horizontal alignment of the text.
 * @param overflow Behavior when the text exceeds its available space.
 * @param softWrap Whether text should wrap at soft line breaks.
 * @param maxLines Maximum number of lines allowed.
 * @param minLines Minimum number of lines reserved for the text.
 * @param style Optional complete style override. When provided, it replaces
 * the typography style resolved from the active theme for [variant].
 *
 * Example:
 * ```
 * UiText(
 *     text = buildAnnotatedString {
 *         append("Status: ")
 *
 *         withStyle(
 *             SpanStyle(fontWeight = FontWeight.Bold)
 *         ) {
 *             append("Approved")
 *         }
 *     },
 *     variant = UiTextVariant.Body
 * )
 * ```
 */
@Composable
fun UiText(
    text: AnnotatedString,
    modifier: Modifier = Modifier,
    variant: UiTextVariant = UiTextVariant.Body,
    color: Color = Color.Unspecified,
    textAlign: TextAlign? = null,
    overflow: TextOverflow = TextOverflow.Clip,
    softWrap: Boolean = true,
    maxLines: Int = Int.MAX_VALUE,
    minLines: Int = 1,
    style: TextStyle? = null
) {
    Text(
        text = text,
        modifier = modifier,
        color = color,
        textAlign = textAlign,
        overflow = overflow,
        softWrap = softWrap,
        maxLines = maxLines,
        minLines = minLines,
        style = style ?: variant.toTextStyle()
    )
}
