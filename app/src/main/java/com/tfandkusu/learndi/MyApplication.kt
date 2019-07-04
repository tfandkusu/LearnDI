package com.tfandkusu.learndi

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

class MyApplication : Application() {
    @Suppress("USELESS_CAST")
    override fun onCreate() {
        super.onCreate()
        // この場所はアプリプロセス開始時に1回だけ呼ばれる
        val appModule = module {
            single { CardLocalDataStoreImpl() as CardLocalDataStore }
            single { CardRemoteDataStoreImpl() as CardRemoteDataStore }
            single { CardRepositoryImpl(get(),get()) as CardRepository }
            viewModel { MainPresenter(get()) }
        }
        startKoin {
            androidContext(applicationContext)
            modules(appModule)
        }
    }
}