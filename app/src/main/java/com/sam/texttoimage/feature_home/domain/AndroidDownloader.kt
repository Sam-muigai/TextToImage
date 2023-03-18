package com.sam.texttoimage.feature_home.domain

import android.app.DownloadManager
import android.content.Context
import android.os.Environment
import androidx.core.net.toUri

class AndroidDownloader(
    context: Context
) : Downloader {
    private val downloadManager = context.getSystemService(DownloadManager::class.java)

    override fun downloadFile(url: String): Long {
        val request = DownloadManager.Request(url.toUri())
            .setMimeType("image/jpeg")
            .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
            .setTitle("download.jpg")
            .setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS,"download.jpg")
        return downloadManager.enqueue(request)
    }

}