package br.com.newsapp.repository.core.modules

import org.koin.dsl.module
import org.koin.android.ext.koin.androidContext

import retrofit2.Retrofit

import br.com.newsapp.repository.api.ApiService
import br.com.newsapp.repository.core.MicroServiceInterceptor
import br.com.newsapp.repository.core.OkHttp3
import br.com.newsapp.repository.core.RepositoryServer
import br.com.newsapp.repository.core.RetrofitMobile
import br.com.newsapp.repository.core.preferences.Preferences
import br.com.newsapp.repository.core.preferences.SharedPreferencesImpl

val networkModule = module {
    single<Preferences> { SharedPreferencesImpl(androidContext()) }
    single { RetrofitMobile(androidContext(), get()) }
    single {(get() as Retrofit).create(ApiService::class.java) }

    factory { OkHttp3(get(), androidContext()) }
    factory { MicroServiceInterceptor(androidContext()) }
    factory { RepositoryServer(get()) }
}