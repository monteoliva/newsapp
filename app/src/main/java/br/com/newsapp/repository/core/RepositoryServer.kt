package br.com.newsapp.repository.core

import br.com.newsapp.repository.api.ApiService
import br.com.newsapp.repository.core.extensions.serverWrapper
import br.com.newsapp.repository.model.topHeadlines.TopHeadlines
import br.com.newsapp.repository.model.WsResult

class RepositoryServer(private val apiService: ApiService) {
    fun getBreakingNews(callback: (WsResult<TopHeadlines>) -> Unit) {
        apiService
            .getBreakingNews()
            .serverWrapper { callback.invoke(it) }
    }
}