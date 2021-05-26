package com.example.cleanarchitecture.tvshows

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.cleanarchitecture.core.data.Resource
import com.example.cleanarchitecture.core.domain.model.Showbiz
import com.example.cleanarchitecture.core.domain.usecase.AppUseCase


class TvShowsViewModel(private val appUseCase: AppUseCase) : ViewModel() {
    fun getTvShows(sort: String): LiveData<Resource<List<Showbiz>>> =
        appUseCase.getAllTvShows(sort).asLiveData()
}