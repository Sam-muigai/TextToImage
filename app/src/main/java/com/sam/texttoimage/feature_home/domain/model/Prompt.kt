package com.sam.texttoimage.feature_home.domain.model

data class Prompt(
    val n: Int,
    val prompt: String,
    val size: String
)