package com.sam.texttoimage.components


import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.placeholder
import com.google.accompanist.placeholder.shimmer
import com.sam.texttoimage.R
import com.sam.texttoimage.feature_home.presentation.destinations.SettingsDialogDestination
import com.sam.texttoimage.ui.theme.Blue


@Composable
fun SearchField(
    modifier: Modifier = Modifier,
    text: String,
    placeholder: String? = null,
    onClearClicked: () -> Unit,
    onSearch: () -> Unit,
    onSettingsClicked:()->Unit,
    onValueChange: (String) -> Unit,
) {
   Column (modifier = modifier){
       Spacer(modifier = Modifier.height(40.dp))
       Row(
           modifier = Modifier.fillMaxWidth(),
           verticalAlignment = Alignment.CenterVertically
       ){
           TextField(
               modifier = Modifier.weight(1f),
               value = text,
               onValueChange = { onValueChange(it) },
               leadingIcon = {
                   Icon(
                       imageVector = Icons.Default.Search,
                       contentDescription = stringResource(id = R.string.search)
                   )
               },
               singleLine = true,
               shape = MaterialTheme.shapes.large,
               keyboardOptions = KeyboardOptions(
                   imeAction = ImeAction.Search
               ),
               keyboardActions = KeyboardActions(
                   onSearch = {
                       onSearch()
                   }
               ),
               placeholder = {
                   Text(text = placeholder!!)
               },
               colors = TextFieldDefaults.textFieldColors(
                   focusedIndicatorColor = Color.Transparent,
                   unfocusedIndicatorColor = Color.Transparent,
                   backgroundColor = Color.LightGray
               ),
               trailingIcon = {
                   AnimatedVisibility(text.isNotEmpty()) {
                       Icon(
                           modifier = Modifier.clickable { onClearClicked() },
                           imageVector = Icons.Default.Close,
                           contentDescription = stringResource(id = R.string.clear_text)
                       )
                   }
               }
           )
           Icon(
               modifier = Modifier
                   .size(35.dp)
                   .clickable { onSettingsClicked() },
               imageVector = Icons.Default.Settings,
               contentDescription = stringResource(id = R.string.settings),
               tint = MaterialTheme.colors.onBackground
           )
       }
   }
}

@Composable
fun Image(
    modifier: Modifier = Modifier,
    isVisible: Boolean,
    imageUrl: String? = null,
    onClick: () -> Unit = {}
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp)
            .height(480.dp)
            .clickable {
                onClick()
            }
            .placeholder(
                visible = isVisible,
                color = Color.Gray,
                shape = RoundedCornerShape(24.dp),
                highlight = PlaceholderHighlight.shimmer(
                    highlightColor = Blue
                )
            ),
        content = {
            AsyncImage(
                modifier = Modifier.size(500.dp),
                model = ImageRequest
                    .Builder(LocalContext.current)
                    .data(imageUrl)
                    .crossfade(1000)
                    .build(),
                contentDescription = stringResource(id = R.string.image_loaded),
                contentScale = ContentScale.Crop
            )
        },
        shape = RoundedCornerShape(24.dp),
        backgroundColor = Blue
    )
}
