package com.example.cleanarchitecture.detail

import androidx.lifecycle.ViewModel
import com.example.cleanarchitecture.core.domain.model.Showbiz
import com.example.cleanarchitecture.core.domain.usecase.AppUseCase


class DetailViewModel(private val appUseCase : AppUseCase) : ViewModel() {

    fun setFavoriteMovie(showbix : Showbiz, newStatus: Boolean) {
        appUseCase.setMovieFavorite(showbix, newStatus)
    }
}
