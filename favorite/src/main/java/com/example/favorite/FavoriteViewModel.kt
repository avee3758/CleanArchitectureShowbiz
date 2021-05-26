package com.example.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.cleanarchitecture.core.domain.model.Showbiz
import com.example.cleanarchitecture.core.domain.usecase.AppUseCase

class FavoriteViewModel(private val appUseCase: AppUseCase) : ViewModel() {

    fun getFavoriteMovies(sort: String): LiveData<List<Showbiz>> =
        appUseCase.getFavoriteMovies(sort).asLiveData()

    fun getFavoriteTvShows(sort: String): LiveData<List<Showbiz>> =
        appUseCase.getFavoriteTvShows(sort).asLiveData()
}

