package com.sam.texttoimage.feature_home.data

import com.haroldadmin.cnradapter.NetworkResponse
import com.sam.texttoimage.common.Resources
import com.sam.texttoimage.feature_home.data.model.ImagesDto
import com.sam.texttoimage.feature_home.data.model.PromptDto
import com.sam.texttoimage.feature_home.domain.TextToImageRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class TextToImageRepositoryImpl @Inject constructor(
    private val api: OpenAiApi
) : TextToImageRepository {

    override fun getImages(promptDto: PromptDto): Flow<Resources<ImagesDto>> = flow {
        emit(Resources.Loading())
        when (val response = api.getImages(promptDto)) {
            is NetworkResponse.Success -> {
                val images = response.body
                emit(Resources.Success(data = images))
            }
            is NetworkResponse.ServerError -> {
                emit(
                    Resources.Error(
                        message = response.body?.message ?: "An Error occurred Try Again later"
                    )
                )
            }
            is NetworkResponse.NetworkError -> {
                emit(
                    Resources.Error(
                        message = response.body?.message ?: "Check your Internet connection"
                    )
                )
            }
            is NetworkResponse.UnknownError -> {
                emit(
                    Resources.Error(
                        message = response.body?.message ?: "Unknown error occurred!!"
                    )
                )
            }
        }
    }
}