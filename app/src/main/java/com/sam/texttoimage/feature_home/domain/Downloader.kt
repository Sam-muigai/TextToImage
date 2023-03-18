package com.sam.texttoimage.feature_home.domain

interface Downloader {
    fun downloadFile(url:String):Long
}