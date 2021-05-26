package com.example.cleanarchitecture.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.cleanarchitecture.core.data.Resource
import com.example.cleanarchitecture.core.domain.model.Showbiz
import com.example.cleanarchitecture.core.domain.usecase.AppUseCase

class MoviesViewModel(private val appUseCase: AppUseCase) : ViewModel() {
    fun getMovies(sort: String): LiveData<Resource<List<Showbiz>>> {
        return appUseCase.getAllMovies(sort).asLiveData()
    }
}