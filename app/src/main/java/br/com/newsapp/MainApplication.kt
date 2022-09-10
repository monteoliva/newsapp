package br.com.newsapp

import android.app.Application

import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.loadKoinModules
import org.koin.core.context.startKoin

import br.com.newsapp.repository.core.modules.networkModule
import br.com.newsapp.repository.core.modules.viewModelModule
import br.com.newsapp.repository.core.modules.adapterModule

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@MainApplication)
            loadKoinModules(
                listOf(networkModule, viewModelModule, adapterModule)
            )
        }
    }
}