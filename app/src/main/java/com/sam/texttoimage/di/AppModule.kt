package com.sam.texttoimage.di

import android.content.Context
import androidx.datastore.core.DataStore
import com.haroldadmin.cnradapter.NetworkResponseAdapterFactory
import com.sam.texttoimage.feature_home.data.AppPreferenceRepositoryImpl
import com.sam.texttoimage.feature_home.data.OpenAiApi
import com.sam.texttoimage.feature_home.data.TextToImageRepositoryImpl
import com.sam.texttoimage.feature_home.data.dataStore
import com.sam.texttoimage.feature_home.data.model.AppPreferences
import com.sam.texttoimage.feature_home.domain.AppPreferenceRepository
import com.sam.texttoimage.feature_home.domain.TextToImageRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    private fun loggingInterceptor(): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    }

    @Provides
    @Singleton
    fun provideOpenApi(): OpenAiApi {
        return Retrofit
            .Builder()
            .baseUrl(OpenAiApi.BASE_URL)
            .addCallAdapterFactory(NetworkResponseAdapterFactory())
            .addConverterFactory(GsonConverterFactory.create())
            .client(loggingInterceptor())
            .build()
            .create(OpenAiApi::class.java)
    }

    @Provides
    @Singleton
    fun provideTextImageRepository(api: OpenAiApi):TextToImageRepository{
      return TextToImageRepositoryImpl(api = api)
    }

    @Provides
    @Singleton
    fun provideAppPreferenceRepository(@ApplicationContext context: Context):AppPreferenceRepository{
        val appPreferences: DataStore<AppPreferences> =
            context.dataStore
        return AppPreferenceRepositoryImpl(appPreferences)
    }
}