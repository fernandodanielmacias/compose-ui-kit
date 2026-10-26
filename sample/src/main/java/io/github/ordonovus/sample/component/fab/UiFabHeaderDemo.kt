package io.github.ordonovus.sample.component.fab

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import io.github.ordonovus.component.fab.UiFloatingActionButton
import io.github.ordonovus.component.text.UiText
import io.github.ordonovus.component.text.UiTextVariant
import io.github.ordonovus.sample.R
import io.github.ordonovus.sample.ui.layout.SampleDemoContainer
import io.github.ordonovus.theme.UiDimensSmall

/**
 * Displays an interactive floating action button demonstration.
 *
 * Each click increments a counter, showing how a floating action
 * button can trigger an action and update the surrounding interface.
 *
 * @param modifier Modifier applied to the demonstration container.
 */
@Composable
fun UiFabHeaderDemo(
    modifier: Modifier = Modifier
) {
    var count by rememberSaveable {
        mutableIntStateOf(0)
    }

    SampleDemoContainer(
        modifier = modifier
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(UiDimensSmall),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            UiFloatingActionButton(
                onClick = {
                    count++
                }
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = stringResource(
                        R.string.sample_fab_add
                    )
                )
            }

            UiText(
                text = stringResource(
                    R.string.sample_fab_header_counter,
                    count
                ),
                variant = UiTextVariant.BodySecondary
            )
        }
    }
}