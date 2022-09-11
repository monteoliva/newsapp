package br.com.newsapp.repository.core.extensions

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

import br.com.newsapp.repository.model.WsResult

fun <T> Call<T>.serverWrapper(callback: (WsResult<T>) -> Unit) {
    this.apply {
        enqueue(object : Callback<T> {
            override fun onResponse(call: Call<T>, response: Response<T>) {
                if (response.code() == 200) {
                    response.body()?.let { result ->
                        callback.invoke(WsResult<T>().also {
                            it.codeError = 0
                            it.message   = "Erro no sistema. Entre em contato com o administrador"
                            it.result    = result
                        })
                    }
                }
                else {
                    callback.invoke(WsResult<T>().also {
                        it.codeError = 9
                        it.message   = "Erro no sistema. Entre em contato com o administrador"
                    })
                }
            }
            override fun onFailure(call: Call<T>, t: Throwable) {
                callback.invoke(WsResult<T>().also {
                    it.codeError = 9
                    it.message   = "Erro no sistema. Entre em contato com o administrador"
                })
            }
        })
    }
}