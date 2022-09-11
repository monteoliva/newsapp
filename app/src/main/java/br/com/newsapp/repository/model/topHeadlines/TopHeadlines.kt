package br.com.newsapp.repository.model.topHeadlines

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class TopHeadlines(
    @SerializedName("articles")
    val articles: MutableList<Article>? = null,
    @SerializedName("status")
    val status: String? = null,
    @SerializedName("totalResults")
    val totalResults: Int? = null,
    @SerializedName("code")
    val code: String? = null,
    @SerializedName("message")
    val message: String? = null
) : Serializable