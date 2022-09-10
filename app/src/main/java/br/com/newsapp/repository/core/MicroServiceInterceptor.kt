package br.com.newsapp.repository.core

import android.content.Context

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

import br.com.newsapp.R

class MicroServiceInterceptor(private val context: Context) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        var request: Request = chain.request()
        val requestBuilder = request.newBuilder()
            .header("Cache-Control", "no-cache")
            .header("Content-Type" , "application/json; charset=UTF-8")
            .header("X-Api-Key"    , context.getString(R.string.API_TOKEN))

        request = requestBuilder.build()
        return chain.proceed(request)
    }
}
