package io.github.ordonovus.sample.component.fab

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import io.github.ordonovus.component.fab.UiExtendedFloatingActionButton
import io.github.ordonovus.component.fab.UiFloatingActionButton
import io.github.ordonovus.component.fab.UiFloatingActionButtonDefaults
import io.github.ordonovus.component.fab.UiLargeFloatingActionButton
import io.github.ordonovus.component.fab.UiSmallFloatingActionButton
import io.github.ordonovus.component.text.UiText
import io.github.ordonovus.sample.R
import io.github.ordonovus.sample.ui.feedback.LocalSampleSnackbarHostState
import io.github.ordonovus.sample.ui.feedback.showSampleMessage
import io.github.ordonovus.sample.ui.layout.SampleComponentHeader
import io.github.ordonovus.sample.ui.layout.SampleSection
import io.github.ordonovus.sample.ui.preview.SamplePreview
import io.github.ordonovus.sample.ui.preview.SamplePreviewDevice
import io.github.ordonovus.theme.UiDimensLarge
import io.github.ordonovus.theme.UiDimensMedium
import io.github.ordonovus.theme.UiShapeNone
import io.github.ordonovus.theme.UiShapesCut

/**
 * Displays the floating action button showcase used by the sample module.
 *
 * Demonstrates the standard, small, large, and extended variants,
 * semantic color roles, expandable behavior, and shape customization.
 *
 * Interactive examples provide feedback through the shared snackbar host.
 */
@Composable
fun UiFabSample() {
    val snackbarHostState = LocalSampleSnackbarHostState.current
    val coroutineScope = rememberCoroutineScope()

    var expanded by rememberSaveable {
        mutableStateOf(true)
    }

    val actionMessage = stringResource(R.string.sample_fab_action_message)
    val primaryMessage = stringResource(R.string.sample_fab_primary_message)
    val secondaryMessage = stringResource(R.string.sample_fab_secondary_message)
    val tertiaryMessage = stringResource(R.string.sample_fab_tertiary_message)

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(UiDimensMedium),
        verticalArrangement = Arrangement.spacedBy(UiDimensLarge)
    ) {
        item {
            SampleComponentHeader(
                title = stringResource(R.string.sample_fab_title),
                description = stringResource(R.string.sample_fab_description),
                demo = {
                    UiFabHeaderDemo()
                }
            )
        }

        item {
            SampleSection(
                title = stringResource(R.string.sample_fab_variants_title),
                description = stringResource(R.string.sample_fab_variants_description)
            ) {
                FlowRow(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(UiDimensMedium),
                    verticalArrangement = Arrangement.spacedBy(UiDimensMedium),
                    itemVerticalAlignment = Alignment.CenterVertically
                ) {
                    UiSmallFloatingActionButton(
                        onClick = {
                            snackbarHostState.showSampleMessage(
                                coroutineScope,
                                actionMessage
                            )
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.Add,
                            contentDescription = stringResource(
                                R.string.sample_fab_small
                            )
                        )
                    }

                    UiFloatingActionButton(
                        onClick = {
                            snackbarHostState.showSampleMessage(
                                coroutineScope,
                                actionMessage
                            )
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.Add,
                            contentDescription = stringResource(
                                R.string.sample_fab_standard
                            )
                        )
                    }

                    UiLargeFloatingActionButton(
                        onClick = {
                            snackbarHostState.showSampleMessage(
                                coroutineScope,
                                actionMessage
                            )
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.Add,
                            contentDescription = stringResource(
                                R.string.sample_fab_large
                            ),
                            modifier = Modifier.size(
                                UiFloatingActionButtonDefaults.LargeIconSize
                            )
                        )
                    }

                    UiExtendedFloatingActionButton(
                        onClick = {
                            snackbarHostState.showSampleMessage(
                                coroutineScope,
                                actionMessage
                            )
                        },
                        text = {
                            UiText(
                                text = stringResource(R.string.sample_fab_add)
                            )
                        },
                        icon = {
                            Icon(
                                imageVector = Icons.Default.Add,
                                contentDescription = null
                            )
                        }
                    )
                }
            }
        }

        item {
            SampleSection(
                title = stringResource(R.string.sample_fab_colors_title),
                description = stringResource(R.string.sample_fab_colors_description)
            ) {
                FlowRow(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(UiDimensMedium),
                    verticalArrangement = Arrangement.spacedBy(UiDimensMedium)
                ) {
                    UiFloatingActionButton(
                        onClick = {
                            snackbarHostState.showSampleMessage(
                                coroutineScope,
                                primaryMessage
                            )
                        },
                        containerColor =
                            UiFloatingActionButtonDefaults.PrimaryContainerColor,
                        contentColor =
                            UiFloatingActionButtonDefaults.PrimaryContentColor
                    ) {
                        Icon(
                            imageVector = Icons.Default.Add,
                            contentDescription = stringResource(
                                R.string.sample_fab_primary
                            )
                        )
                    }

                    UiFloatingActionButton(
                        onClick = {
                            snackbarHostState.showSampleMessage(
                                coroutineScope,
                                secondaryMessage
                            )
                        },
                        containerColor =
                            UiFloatingActionButtonDefaults.SecondaryContainerColor,
                        contentColor =
                            UiFloatingActionButtonDefaults.SecondaryContentColor
                    ) {
                        Icon(
                            imageVector = Icons.Default.Add,
                            contentDescription = stringResource(
                                R.string.sample_fab_secondary
                            )
                        )
                    }

                    UiFloatingActionButton(
                        onClick = {
                            snackbarHostState.showSampleMessage(
                                coroutineScope,
                                tertiaryMessage
                            )
                        },
                        containerColor =
                            UiFloatingActionButtonDefaults.TertiaryContainerColor,
                        contentColor =
                            UiFloatingActionButtonDefaults.TertiaryContentColor
                    ) {
                        Icon(
                            imageVector = Icons.Default.Add,
                            contentDescription = stringResource(
                                R.string.sample_fab_tertiary
                            )
                        )
                    }
                }
            }
        }

        item {
            SampleSection(
                title = stringResource(R.string.sample_fab_expanded_title),
                description = stringResource(R.string.sample_fab_expanded_description)
            ) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(UiDimensMedium)
                ) {
                    UiExtendedFloatingActionButton(
                        onClick = {
                            expanded = !expanded
                        },
                        expanded = expanded,
                        text = {
                            UiText(
                                text = stringResource(R.string.sample_fab_edit)
                            )
                        },
                        icon = {
                            Icon(
                                imageVector = Icons.Default.Edit,
                                contentDescription = stringResource(
                                    R.string.sample_fab_toggle
                                )
                            )
                        }
                    )

                    UiText(
                        text = stringResource(
                            if (expanded) {
                                R.string.sample_fab_expanded_state
                            } else {
                                R.string.sample_fab_collapsed_state
                            }
                        )
                    )
                }
            }
        }

        item {
            SampleSection(
                title = stringResource(R.string.sample_fab_customization_title),
                description = stringResource(
                    R.string.sample_fab_customization_description
                )
            ) {
                FlowRow(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(UiDimensMedium),
                    verticalArrangement = Arrangement.spacedBy(UiDimensMedium)
                ) {
                    UiFloatingActionButton(
                        onClick = {
                            snackbarHostState.showSampleMessage(
                                coroutineScope,
                                actionMessage
                            )
                        },
                        shape = UiShapesCut.medium
                    ) {
                        Icon(
                            imageVector = Icons.Default.Edit,
                            contentDescription = stringResource(
                                R.string.sample_fab_cut_shape
                            )
                        )
                    }

                    UiFloatingActionButton(
                        onClick = {
                            snackbarHostState.showSampleMessage(
                                coroutineScope,
                                actionMessage
                            )
                        },
                        shape = UiShapeNone
                    ) {
                        Icon(
                            imageVector = Icons.Default.Edit,
                            contentDescription = stringResource(
                                R.string.sample_fab_square_shape
                            )
                        )
                    }

                    UiFloatingActionButton(
                        onClick = {
                            snackbarHostState.showSampleMessage(
                                coroutineScope,
                                actionMessage
                            )
                        },
                        elevation =
                            UiFloatingActionButtonDefaults.loweredElevation(),
                        containerColor =
                            MaterialTheme.colorScheme.surfaceContainerHigh,
                        contentColor =
                            MaterialTheme.colorScheme.onSurface
                    ) {
                        Icon(
                            imageVector = Icons.Default.Edit,
                            contentDescription = stringResource(
                                R.string.sample_fab_lowered_elevation
                            )
                        )
                    }
                }
            }
        }
    }
}

/**
 * Previews the floating action button showcase using the light theme.
 */
@Preview(
    name = "FAB - Light",
    showBackground = true,
    showSystemUi = true,
    device = SamplePreviewDevice
)
@Composable
private fun UiFabSampleLightPreview() {
    SamplePreview {
        UiFabSample()
    }
}

/**
 * Previews the floating action button showcase using the dark theme.
 */
@Preview(
    name = "FAB - Dark",
    showBackground = true,
    showSystemUi = true,
    device = SamplePreviewDevice
)
@Composable
private fun UiFabSampleDarkPreview() {
    SamplePreview(darkTheme = true) {
        UiFabSample()
    }
}