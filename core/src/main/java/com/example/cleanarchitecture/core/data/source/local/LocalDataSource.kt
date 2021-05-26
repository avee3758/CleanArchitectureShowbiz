package com.example.cleanarchitecture.core.data.source.local

import com.example.cleanarchitecture.core.data.source.local.entity.Entity
import com.example.cleanarchitecture.core.data.source.local.room.AppDao
import com.example.cleanarchitecture.core.utils.Queries
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flowOn

class LocalDataSource(private val mAppDao: AppDao) {

    fun getAllMovies(sort: String): Flow<List<Entity>> {
        val query = Queries.getQueryMovies(sort)
        return mAppDao.getMovies(query)
    }

    fun getAllTvShows(sort: String): Flow<List<Entity>> {
        val query = Queries.getQueryTvShows(sort)
        return mAppDao.getTvShows(query)
    }

    fun getAllFavoriteMovies(sort: String): Flow<List<Entity>> {
        val query = Queries.getQueryFavoriteMovies(sort)
        return mAppDao.getFavoriteMovies(query)
    }

    fun getAllFavoriteTvShows(sort: String): Flow<List<Entity>> {
        val query = Queries.getQueryFavoriteTvShows(sort)
        return mAppDao.getFavoriteTvShows(query)
    }

    fun movieSearch(search: String): Flow<List<Entity>> {
        return mAppDao.searchMovies(search)
            .flowOn(Dispatchers.Default)
            .conflate()
    }

    fun tvShowSearch(search: String): Flow<List<Entity>> {
        return mAppDao.searchTvShows(search)
            .flowOn(Dispatchers.Default)
            .conflate()
    }

    suspend fun insertMovies(showbiz: List<Entity>) = mAppDao.insertMovie(showbiz)

    fun setMovieFavorite(showbiz: Entity, newState: Boolean) {
        showbiz.favorite = newState
        mAppDao.updateFavoriteMovie(showbiz)
    }
}