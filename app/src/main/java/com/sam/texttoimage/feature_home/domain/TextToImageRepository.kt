package com.sam.texttoimage.feature_home.domain

import com.sam.texttoimage.common.Resources
import com.sam.texttoimage.feature_home.data.model.ImagesDto
import com.sam.texttoimage.feature_home.data.model.PromptDto
import kotlinx.coroutines.flow.Flow

interface TextToImageRepository{

    fun getImages(promptDto: PromptDto): Flow<Resources<ImagesDto>>

}