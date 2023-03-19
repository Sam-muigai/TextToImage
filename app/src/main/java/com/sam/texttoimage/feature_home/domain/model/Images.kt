package com.sam.texttoimage.feature_home.domain.model

import com.sam.texttoimage.feature_home.domain.model.Data

data class Images(
    val created: Int,
    val data: List<Data>
)

val fakeData = Images(
    111,
    listOf(fakeUrl)
)

