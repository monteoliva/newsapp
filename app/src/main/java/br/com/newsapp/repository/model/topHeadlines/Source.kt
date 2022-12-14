package br.com.newsapp.repository.model.topHeadlines

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Source(
    @SerializedName("id")
    val id: String? = null,
    @SerializedName("name")
    val name: String? = null
) : Serializable