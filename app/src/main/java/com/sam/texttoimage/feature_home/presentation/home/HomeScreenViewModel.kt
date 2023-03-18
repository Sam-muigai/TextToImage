package com.sam.texttoimage.feature_home.presentation.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sam.texttoimage.common.Resources
import com.sam.texttoimage.common.UiEvents
import com.sam.texttoimage.feature_home.data.model.toImages
import com.sam.texttoimage.feature_home.data.model.toPromptDto
import com.sam.texttoimage.feature_home.domain.AppPreferenceRepository
import com.sam.texttoimage.feature_home.domain.TextToImageRepository
import com.sam.texttoimage.feature_home.domain.model.Prompt
import com.sam.texttoimage.feature_home.presentation.home.HomeScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val repository: TextToImageRepository,
    private val appPreferenceRepository: AppPreferenceRepository
) : ViewModel() {

    private var _uiState = MutableStateFlow<HomeScreenState>(HomeScreenState.Empty)
    val uiState = _uiState.asStateFlow()

    private var _uiEvent = MutableSharedFlow<UiEvents>()
    val uiEvents = _uiEvent.asSharedFlow()

    var text by mutableStateOf("")
    fun onTextChange(input:String){
        text = input
    }
    fun onClearText(){
        text = ""
    }
    private val size = mutableStateOf("")

    init {
        viewModelScope.launch {
            appPreferenceRepository.appPreferences.collect{
                size.value = it.resolution.resolution
            }
        }
    }


    fun getImages() {
        val prompt = Prompt(
            n = 6,
            prompt = text,
            size = size.value
        )
        viewModelScope.launch {
            repository.getImages(prompt.toPromptDto()).collect {
                when (it) {
                    is Resources.Success -> {
                        val images = it.data
                        _uiState.value = HomeScreenState.Success(images?.toImages()!!)
                    }
                    is Resources.Error -> {
                        _uiState.value = HomeScreenState.Error(it.message!!)
                        showMessage(it.message)
                    }
                    is Resources.Loading -> { _uiState.value = HomeScreenState.Loading }
                }
            }
        }
    }

    private fun showMessage(message: String) {
        viewModelScope.launch {
            _uiEvent.emit(
                UiEvents.ShowSnackBar(message)
            )
        }
    }
}