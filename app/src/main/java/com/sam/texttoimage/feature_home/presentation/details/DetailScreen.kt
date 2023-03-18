package com.sam.texttoimage.feature_home.presentation.details

import android.annotation.SuppressLint
import android.app.WallpaperManager
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Message
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.sam.texttoimage.R
import com.sam.texttoimage.common.setWallpaperFromUrl
import com.sam.texttoimage.common.showMessage
import com.sam.texttoimage.feature_home.domain.AndroidDownloader
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.net.URL
import java.net.URLClassLoader


@SuppressLint("ResourceType")
@Destination
@Composable
fun DetailScreen(
    navigator: DestinationsNavigator,
    imageUrl: String
) {
    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    val loading = remember {
        mutableStateOf(false)
    }
    val downloader = AndroidDownloader(context)
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background)
    ) {
        AsyncImage(
            modifier = Modifier
                .align(Alignment.Center)
                .fillMaxSize(),
            model = ImageRequest
                .Builder(LocalContext.current)
                .data(imageUrl)
                .crossfade(true)
                .build(),
            contentDescription = null,
            contentScale = ContentScale.Crop
        )

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Bottom
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                if (!loading.value) {
                    CustomButton(
                        text = stringResource(id = R.string.set_wallpaper)
                    ) {
                        loading.value = true
                        scope.launch(Dispatchers.IO) {
                            val message = setWallpaperFromUrl(imageUrl, context) {
                                loading.value = false
                            }
                            withContext(Dispatchers.Main) {
                                showMessage(context, message)
                            }
                        }
                    }
                } else {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .padding(
                                bottom = 56.dp,
                                end = 8.dp,
                                start = 8.dp
                            )
                    )
                }
            }
            CustomButton(
                text = stringResource(id = R.string.download_image)
            ) {
                downloader.downloadFile(imageUrl)
                showMessage(context, "Download Started!")
            }
            Spacer(modifier = Modifier.height(40.dp))
        }
    }
}

@Preview
@Composable
fun CustomButton(
    modifier: Modifier = Modifier,
    text: String = "SET AS WALLPAPER",
    onClick: () -> Unit = {}
) {
    Column {
        Button(
            modifier = modifier
                .fillMaxWidth()
                .padding(8.dp),
            onClick = onClick
        ) {
            Text(text = text)
        }
    }
}


