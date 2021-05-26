package com.example.cleanarchitecture.core.data.source.remote

import com.example.cleanarchitecture.core.data.source.remote.network.ApiResponse
import com.example.cleanarchitecture.core.data.source.remote.network.ApiService
import com.example.cleanarchitecture.core.data.source.remote.response.MovieResponseList
import com.example.cleanarchitecture.core.data.source.remote.response.TvShowResponseList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RemoteDataSource(private val apiService: ApiService) {

    private val apiKey = "cd0ab9f09d08d6af957c232835f9002e" //REPLACE YOUR API KEY HERE!!

    suspend fun getMovies(): Flow<ApiResponse<List<MovieResponseList>>> {
        return flow {
            try {
                val response = apiService.getMovies(apiKey)
                val movieList = response.results
                if (movieList.isNotEmpty()) {
                    emit(ApiResponse.Success(response.results))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getTvShows(): Flow<ApiResponse<List<TvShowResponseList>>> {
        return flow {
            try {
                val response = apiService.getTvShows(apiKey)
                val tvShowList = response.results
                if (tvShowList.isNotEmpty()) {
                    emit(ApiResponse.Success(response.results))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }

}
