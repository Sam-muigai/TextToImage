package com.sam.texttoimage.feature_home.presentation.settings

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.spec.DestinationStyle
import com.sam.texttoimage.R
import com.sam.texttoimage.feature_home.data.model.AppPreferences
import com.sam.texttoimage.feature_home.data.model.Resolution
import com.sam.texttoimage.ui.theme.Theme

@Destination(style = MyDialogStyle::class)
@Composable
fun SettingsDialog(viewModel: SettingsViewModel = hiltViewModel()) {
    val preferences = viewModel.preferences.collectAsState(initial = AppPreferences()).value
    Card(
        modifier = Modifier.width(240.dp),
        shape = MaterialTheme.shapes.small
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Text(
                text = stringResource(id = R.string.settings),
                style = MaterialTheme.typography.h6.copy(
                    fontWeight = FontWeight.Bold
                )
            )
            Divider()
            Text(
                text = stringResource(id = R.string.theme_preference),
                style = MaterialTheme.typography.body1.copy(
                    fontWeight = FontWeight.Bold
                )
            )
            Column(
                modifier = Modifier
                    .width(160.dp)
                    .padding(4.dp)
            ) {
                for (i in 0..2) {
                    val theme = Theme.values()[i]
                    Row(
                        modifier = Modifier,
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Start
                    ) {
                        RadioButton(selected = theme == preferences.theme,
                            onClick = {
                                viewModel.setTheme(theme)
                            }
                        )
                        Text(text = theme.themeName)
                    }
                }
            }
            Text(
                text = "Preferred Resolution",
                style = MaterialTheme.typography.body1.copy(
                    fontWeight = FontWeight.Bold
                )
            )
            Column(
                modifier = Modifier
                    .width(160.dp)
                    .padding(4.dp)
            ) {
                for (i in 0..2) {
                    val resolution = Resolution.values()[i]
                    Row(
                        modifier = Modifier,
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Start
                    ) {
                        RadioButton(selected = resolution == preferences.resolution,
                            onClick = {
                                viewModel.setResolution(resolution)
                            }
                        )
                        Text(text = resolution.resolution)
                    }
                }
            }
            Divider()
            Text(
                modifier = Modifier.padding(start = 4.dp),
                text = stringResource(id = R.string.app_version),
                style = MaterialTheme.typography.caption
            )
        }
    }
}

object MyDialogStyle : DestinationStyle.Dialog {
    override val properties: DialogProperties
        get() = DialogProperties(
            dismissOnBackPress = true,
            dismissOnClickOutside = true
        )
}