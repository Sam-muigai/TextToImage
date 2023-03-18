package com.sam.texttoimage.feature_home.data

import android.content.Context
import androidx.datastore.core.Serializer
import androidx.datastore.dataStore
import com.sam.texttoimage.feature_home.data.model.AppPreferences
import kotlinx.serialization.SerializationException
import kotlinx.serialization.json.Json

import java.io.InputStream
import java.io.OutputStream


@Suppress("BlockingMethodInNonBlockingContext")
object AppPreferenceSerializer: Serializer<AppPreferences>{
    override val defaultValue: AppPreferences
        get() = AppPreferences()

    override suspend fun readFrom(input: InputStream): AppPreferences {
        return try {
            Json.decodeFromString(
                deserializer = AppPreferences.serializer(),
                string = input.readBytes().decodeToString()
            )
        }catch (e :SerializationException){
            e.printStackTrace()
            AppPreferences()
        }
    }

    override suspend fun writeTo(t: AppPreferences, output: OutputStream) {
        output.write(
            Json.encodeToString(
                serializer = AppPreferences.serializer(),
                value = t
            ).encodeToByteArray()
        )
    }
}

val Context.dataStore by dataStore("app-preference.json",AppPreferenceSerializer)
