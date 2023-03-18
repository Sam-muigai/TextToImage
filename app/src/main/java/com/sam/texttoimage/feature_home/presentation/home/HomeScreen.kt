package com.sam.texttoimage.feature_home.presentation.home

import android.widget.RatingBar
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.sam.texttoimage.R
import com.sam.texttoimage.common.UiEvents
import com.sam.texttoimage.components.Image
import com.sam.texttoimage.components.SearchField
import com.sam.texttoimage.feature_home.domain.model.Data
import com.sam.texttoimage.feature_home.presentation.destinations.DetailScreenDestination
import com.sam.texttoimage.feature_home.presentation.destinations.SettingsDialogDestination

@RootNavGraph(start = true)
@Destination
@Composable
fun HomeScreen(
    viewModel: HomeScreenViewModel = hiltViewModel(),
    navigator: DestinationsNavigator
) {
    val scaffoldState = rememberScaffoldState()
    LaunchedEffect(key1 = true) {
        viewModel.uiEvents.collect {
            when (it) {
                is UiEvents.ShowSnackBar -> {
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = it.message
                    )
                }
            }
        }
    }
    val uiState = viewModel.uiState.collectAsState().value
    Scaffold(
        scaffoldState = scaffoldState
    ) { paddingValues ->
        HomeScreen(
            modifier = Modifier.padding(paddingValues),
            homeScreenState = uiState,
            onImageClicked = {
                navigator.navigate(DetailScreenDestination(it))
            },
            value = viewModel.text,
            onClearClicked = viewModel::onClearText,
            onSearch = viewModel::getImages,
            onSettingsClicked = { navigator.navigate(SettingsDialogDestination) },
            onValueChange = viewModel::onTextChange
        )
    }
}

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    homeScreenState: HomeScreenState,
    onImageClicked: (String) -> Unit,
    value: String,
    onClearClicked: () -> Unit,
    onSearch: () -> Unit,
    onSettingsClicked: () -> Unit,
    onValueChange: (String) -> Unit,
) {
    val focusManager = LocalFocusManager.current
    val lazyColumnState = rememberLazyListState()
    Box(
        modifier = modifier
            .fillMaxSize()
            .padding()
            .background(MaterialTheme.colors.background)
    ) {
        when (homeScreenState) {
            is HomeScreenState.Loading -> {
                LoadingScreen()
            }
            is HomeScreenState.Error -> {
                ErrorScreen()
            }
            is HomeScreenState.Success -> {
                Images(
                    data = homeScreenState.data.data,
                    isVisible = false,
                    lazyColumnState = lazyColumnState
                ) {
                    onImageClicked(it)
                }
            }
            else -> Unit
        }
        val showSearchBar = remember {
            derivedStateOf {
                lazyColumnState.firstVisibleItemIndex == 0 || homeScreenState == HomeScreenState.Empty
            }
        }
        AnimatedVisibility(
            visible = showSearchBar.value,
            enter = slideInVertically(),
            exit = slideOutVertically()
        ) {
            SearchField(
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(8.dp),
                text = value,
                placeholder = stringResource(id = R.string.prompt),
                onClearClicked = onClearClicked,
                onSearch = {
                    focusManager.clearFocus()
                    onSearch()
                },
                onValueChange = onValueChange,
                onSettingsClicked = onSettingsClicked
            )
        }
    }
}


@Composable
fun ErrorScreen() {
    Box(modifier = Modifier.fillMaxSize()) {
        val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.error))
        LottieAnimation(
            modifier = Modifier
                .fillMaxWidth()
                .height(240.dp)
                .align(Alignment.Center)
                .semantics { contentDescription = "Error Animation" },
            iterations = LottieConstants.IterateForever,
            composition = composition,
        )
    }
}


@Composable
fun Images(
    data: List<Data>,
    lazyColumnState: LazyListState,
    isVisible: Boolean,
    onClick: (String) -> Unit
) {
    val padding = 8.dp
    LazyColumn(
        contentPadding = PaddingValues(padding),
        verticalArrangement = Arrangement.spacedBy(padding),
        state = lazyColumnState
    ) {
        item {
            Spacer(modifier = Modifier.height(95.dp))
        }
        items(data) {
            Image(
                isVisible = isVisible,
                imageUrl = it.url,
            ) {
                onClick(it.url)
            }
        }
    }
}

@Composable
fun LoadingScreen() {
    val padding = 8.dp
    LazyColumn(
        modifier = Modifier
            .semantics { contentDescription = "Loading" },
        contentPadding = PaddingValues(padding),
        verticalArrangement = Arrangement.spacedBy(padding),
    ) {
        item {
            Spacer(modifier = Modifier.height(95.dp))
        }
        items(6) {
            Image(
                modifier = Modifier,
                isVisible = true
            )
        }
    }
}








