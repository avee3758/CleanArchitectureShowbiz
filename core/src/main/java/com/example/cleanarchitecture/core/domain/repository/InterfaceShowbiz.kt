package com.example.cleanarchitecture.core.domain.repository

import com.example.cleanarchitecture.core.data.Resource
import com.example.cleanarchitecture.core.domain.model.Showbiz
import kotlinx.coroutines.flow.Flow

interface InterfaceShowbiz {

    fun getAllMovies(sort: String): Flow<Resource<List<Showbiz>>>

    fun getAllTvShows(sort: String): Flow<Resource<List<Showbiz>>>

    fun getFavoriteMovies(sort: String): Flow<List<Showbiz>>

    fun getFavoriteTvShows(sort: String): Flow<List<Showbiz>>

    fun searchMovies(search: String): Flow<List<Showbiz>>

    fun searchTvShows(search: String): Flow<List<Showbiz>>

    fun setMovieFavorite(movie: Showbiz, state: Boolean)

}