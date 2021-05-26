package com.example.cleanarchitecture.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.cleanarchitecture.core.domain.usecase.AppUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.channels.ConflatedBroadcastChannel
import kotlinx.coroutines.flow.*

@FlowPreview
@ExperimentalCoroutinesApi
class HomeViewModel(private val appUseCase: AppUseCase) : ViewModel() {

    private val queryChannel = ConflatedBroadcastChannel<String>()

    fun setSearchQuery(search: String) {
        queryChannel.offer(search)
    }

    val movieResult = queryChannel.asFlow()
        .debounce(300)
        .distinctUntilChanged()
        .filter {
            it.trim().isNotEmpty()
        }
        .flatMapLatest {
            appUseCase.searchMovies(it)
        }.asLiveData()

    val tvShowResult = queryChannel.asFlow()
        .debounce(300)
        .distinctUntilChanged()
        .filter {
            it.trim().isNotEmpty()
        }
        .flatMapLatest {
            appUseCase.searchTvShows(it)
        }.asLiveData()
}