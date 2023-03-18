package com.sam.texttoimage.feature_home.data

import com.haroldadmin.cnradapter.NetworkResponse
import com.sam.texttoimage.common.ErrorResponse
import com.sam.texttoimage.feature_home.data.model.ImagesDto
import com.sam.texttoimage.feature_home.data.model.PromptDto
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST


interface OpenAiApi{

    @Headers("Authorization: Bearer $API_KEY")
    @POST("images/generations")
    suspend fun getImages(@Body prompt: PromptDto):NetworkResponse<ImagesDto,ErrorResponse>

    companion object{
        const val BASE_URL = "https://api.openai.com/v1/"
        const val API_KEY = "YOUR_API_KEY"
    }

}