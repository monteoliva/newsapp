package br.com.newsapp.repository.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

import br.com.newsapp.repository.model.topHeadlines.TopHeadlines

interface ApiService {
    @GET("/v2/top-headlines")
    fun getBreakingNews(@Query("country") country: String = "br") : Call<TopHeadlines>

}