package com.example.cleanarchitecture.di

import com.example.cleanarchitecture.core.domain.usecase.AppUseCase
import com.example.cleanarchitecture.core.domain.usecase.ShowbizAppInteractor
import com.example.cleanarchitecture.detail.DetailViewModel
import com.example.cleanarchitecture.home.HomeViewModel
import com.example.cleanarchitecture.movies.MoviesViewModel
import com.example.cleanarchitecture.tvshows.TvShowsViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module


val useCaseModule = module {
    factory<AppUseCase> { ShowbizAppInteractor(get()) }
}

@ExperimentalCoroutinesApi
@FlowPreview
val viewModelModule = module {
    viewModel { MoviesViewModel(get()) }
    viewModel { TvShowsViewModel(get()) }
    viewModel { DetailViewModel(get()) }
    viewModel { HomeViewModel(get()) }
}