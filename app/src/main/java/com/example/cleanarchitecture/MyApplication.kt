package com.example.cleanarchitecture

import android.app.Application
import com.example.cleanarchitecture.core.di.LocalDbModule
import com.example.cleanarchitecture.core.di.NetworkModule
import com.example.cleanarchitecture.core.di.RepositroyModule
import com.example.cleanarchitecture.di.useCaseModule
import com.example.cleanarchitecture.di.viewModelModule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

@FlowPreview
@ExperimentalCoroutinesApi
class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@MyApplication)
            modules(
                listOf(
                    LocalDbModule,
                    NetworkModule,
                    RepositroyModule,
                    useCaseModule,
                    viewModelModule
                )
            )
        }
    }
}