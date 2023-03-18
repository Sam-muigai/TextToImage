package com.sam.texttoimage.common

import android.app.WallpaperManager
import android.content.Context
import android.graphics.BitmapFactory
import android.widget.Toast
import java.net.URL

fun showMessage(context: Context, message: String) {
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}


fun setWallpaperFromUrl(imageUrl: String, context: Context, loader: () -> Unit): String {
    val wallpaperManager = WallpaperManager.getInstance(context)
    val inputStream = URL(imageUrl).openStream()
    val bitmap = BitmapFactory.decodeStream(inputStream)
    wallpaperManager.setBitmap(bitmap)
    loader()
    return "Successfully set the wallpaper"
}