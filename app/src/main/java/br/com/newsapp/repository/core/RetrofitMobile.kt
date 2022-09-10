package br.com.newsapp.repository.core

import android.content.Context

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

import okhttp3.OkHttpClient

import br.com.newsapp.R

object RetrofitMobile {
    operator fun invoke(context: Context, okHttpClient: OkHttpClient): Retrofit =
        Retrofit
            .Builder()
            .baseUrl(context.getString(R.string.API_URL))
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
}
