package com.sam.texttoimage.feature_home.presentation.home

import com.sam.texttoimage.feature_home.domain.model.Images

sealed class HomeScreenState{
    data class Success(val data:Images): HomeScreenState()
    object Loading: HomeScreenState()
    data class Error(val message:String): HomeScreenState()
    object Empty: HomeScreenState()
}
