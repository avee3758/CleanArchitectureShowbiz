package com.example.cleanarchitecture.core.domain.usecase

import com.example.cleanarchitecture.core.data.Resource
import com.example.cleanarchitecture.core.domain.model.Showbiz
import com.example.cleanarchitecture.core.domain.repository.InterfaceShowbiz
import kotlinx.coroutines.flow.Flow

class ShowbizAppInteractor(private val showbizRepository: InterfaceShowbiz) : AppUseCase {

    override fun getAllMovies(sort: String): Flow<Resource<List<Showbiz>>> =
        showbizRepository.getAllMovies(sort)

    override fun getAllTvShows(sort: String): Flow<Resource<List<Showbiz>>> =
        showbizRepository.getAllTvShows(sort)

    override fun getFavoriteMovies(sort: String): Flow<List<Showbiz>> =
        showbizRepository.getFavoriteMovies(sort)

    override fun searchMovies(search: String): Flow<List<Showbiz>> =
        showbizRepository.searchMovies(search)

    override fun searchTvShows(search: String): Flow<List<Showbiz>> =
        showbizRepository.searchTvShows(search)

    override fun getFavoriteTvShows(sort: String): Flow<List<Showbiz>> =
        showbizRepository.getFavoriteTvShows(sort)

    override fun setMovieFavorite(movie: Showbiz, state: Boolean) =
        showbizRepository.setMovieFavorite(movie, state)

}