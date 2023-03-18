package com.sam.texttoimage.common

sealed class UiEvents {
    data class ShowSnackBar(val message:String): UiEvents()
}
