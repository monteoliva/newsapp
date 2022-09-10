package br.com.newsapp.repository.core

import java.io.File

import android.content.Context
import okhttp3.Cache
import okhttp3.OkHttpClient

object OkHttp3 {
    operator fun invoke(context: Context): OkHttpClient {
        val httpCacheDirectory = File(context.cacheDir, "responses")
        val cacheSize: Long = 10 * 1024 * 1024
        return OkHttpClient()
            .newBuilder()
            .cache(Cache(httpCacheDirectory, cacheSize))
            .addInterceptor(MicroServiceInterceptor(context))
            .build()
    }
}