package com.example.cleanarchitecture.core.data

import com.example.cleanarchitecture.core.data.source.local.LocalDataSource
import com.example.cleanarchitecture.core.data.source.remote.RemoteDataSource
import com.example.cleanarchitecture.core.data.source.remote.network.ApiResponse
import com.example.cleanarchitecture.core.data.source.remote.response.MovieResponseList
import com.example.cleanarchitecture.core.data.source.remote.response.TvShowResponseList
import com.example.cleanarchitecture.core.domain.model.Showbiz
import com.example.cleanarchitecture.core.domain.repository.InterfaceShowbiz
import com.example.cleanarchitecture.core.utils.AppExecutors
import com.example.cleanarchitecture.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ShowbizRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : InterfaceShowbiz {

    override fun getAllMovies(sort: String): Flow<Resource<List<Showbiz>>> =
        object : NetworkBoundResource<List<Showbiz>, List<MovieResponseList>>() {
            override fun loadFromDB(): Flow<List<Showbiz>> {
                return localDataSource.getAllMovies(sort).map {
                    DataMapper.entitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<Showbiz>?): Boolean {
                return data == null || data.isEmpty()
            }

            override suspend fun createCall(): Flow<ApiResponse<List<MovieResponseList>>> {
                return remoteDataSource.getMovies()
            }

            override suspend fun saveCallResult(data: List<MovieResponseList>) {
                val movieList = DataMapper.movieResponsesToEntities(data)
                localDataSource.insertMovies(movieList)
            }
        }.asFlow()

    override fun getAllTvShows(sort: String): Flow<Resource<List<Showbiz>>> =
        object : NetworkBoundResource<List<Showbiz>, List<TvShowResponseList>>() {
            override fun loadFromDB(): Flow<List<Showbiz>> {
                return localDataSource.getAllTvShows(sort).map {
                    DataMapper.entitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<Showbiz>?): Boolean {
                return data == null || data.isEmpty()
            }

            override suspend fun createCall(): Flow<ApiResponse<List<TvShowResponseList>>> {
                return remoteDataSource.getTvShows()
            }

            override suspend fun saveCallResult(data: List<TvShowResponseList>) {
                val tvShowList = DataMapper.tvShowResponsesToEntities(data)
                localDataSource.insertMovies(tvShowList)
            }
        }.asFlow()

    override fun searchMovies(search: String): Flow<List<Showbiz>> {
        return localDataSource.movieSearch(search).map {
            DataMapper.entitiesToDomain(it)
        }
    }

    override fun searchTvShows(search: String): Flow<List<Showbiz>> {
        return localDataSource.tvShowSearch(search).map {
            DataMapper.entitiesToDomain(it)
        }
    }

    override fun getFavoriteMovies(sort: String): Flow<List<Showbiz>> {
        return localDataSource.getAllFavoriteMovies(sort).map {
            DataMapper.entitiesToDomain(it)
        }
    }

    override fun getFavoriteTvShows(sort: String): Flow<List<Showbiz>> {
        return localDataSource.getAllFavoriteTvShows(sort).map {
            DataMapper.entitiesToDomain(it)
        }
    }

    override fun setMovieFavorite(movie: Showbiz, state: Boolean) {
        val movieEntity = DataMapper.domainToEntity(movie)
        appExecutors.diskIO().execute { localDataSource.setMovieFavorite(movieEntity, state) }
    }
}