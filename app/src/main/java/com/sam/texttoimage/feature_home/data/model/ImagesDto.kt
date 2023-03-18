package com.sam.texttoimage.feature_home.data.model

import com.sam.texttoimage.feature_home.domain.model.Data

data class ImagesDto(
    val created: Int,
    val data: List<Data>
)