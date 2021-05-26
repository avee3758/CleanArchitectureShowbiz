package com.example.cleanarchitecture.core.di

import com.example.cleanarchitecture.core.data.ShowbizRepository
import com.example.cleanarchitecture.core.data.source.local.LocalDataSource
import com.example.cleanarchitecture.core.data.source.remote.RemoteDataSource
import com.example.cleanarchitecture.core.domain.repository.InterfaceShowbiz
import com.example.cleanarchitecture.core.utils.AppExecutors
import org.koin.dsl.module

val RepositroyModule = module {
    single { LocalDataSource(get()) }
    single { RemoteDataSource(get()) }
    factory { AppExecutors() }
    single<InterfaceShowbiz> {ShowbizRepository (get(), get(), get()) }
}