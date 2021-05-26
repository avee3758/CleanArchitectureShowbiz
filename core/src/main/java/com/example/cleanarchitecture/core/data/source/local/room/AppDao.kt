package com.example.cleanarchitecture.core.data.source.local.room

import androidx.room.*
import androidx.sqlite.db.SupportSQLiteQuery
import com.example.cleanarchitecture.core.data.source.local.entity.Entity
import kotlinx.coroutines.flow.Flow

@Dao
interface AppDao {

    @RawQuery(observedEntities = [Entity::class])
    fun getMovies(query: SupportSQLiteQuery): Flow<List<Entity>>

    @RawQuery(observedEntities = [Entity::class])
    fun getTvShows(query: SupportSQLiteQuery): Flow<List<Entity>>

    @Query("SELECT * FROM showbizTable WHERE isTvShow = 0 AND title LIKE '%' || :search || '%'")
    fun searchMovies(search: String): Flow<List<Entity>>

    @Query("SELECT * FROM showbizTable WHERE isTvShow = 1 AND title LIKE '%' || :search || '%'")
    fun searchTvShows(search: String): Flow<List<Entity>>

    @RawQuery(observedEntities = [Entity::class])
    fun getFavoriteMovies(query: SupportSQLiteQuery): Flow<List<Entity>>

    @RawQuery(observedEntities = [Entity::class])
    fun getFavoriteTvShows(query: SupportSQLiteQuery): Flow<List<Entity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(movies: List<Entity>)

    @Update
    fun updateFavoriteMovie(movie: Entity)
}