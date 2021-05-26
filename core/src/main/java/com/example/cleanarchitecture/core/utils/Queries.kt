package com.example.cleanarchitecture.core.utils

import androidx.sqlite.db.SimpleSQLiteQuery

object Queries {
    const val NEWEST = "Newest"
    fun getQueryMovies(filter: String): SimpleSQLiteQuery {
        val simpleQuery = StringBuilder().append("SELECT * FROM showbizTable where isTvShow = 0 ")
        when (filter) {
            NEWEST -> {
                simpleQuery.append("ORDER BY releaseDate DESC")
            }
        }
        return SimpleSQLiteQuery(simpleQuery.toString())
    }

    fun getQueryTvShows(filter: String): SimpleSQLiteQuery {
        val simpleQuery = StringBuilder().append("SELECT * FROM showbizTable where isTvShow = 1 ")
        when (filter) {
            NEWEST -> {
                simpleQuery.append("ORDER BY releaseDate DESC")
            }
        }
        return SimpleSQLiteQuery(simpleQuery.toString())
    }

    fun getQueryFavoriteMovies(filter: String): SimpleSQLiteQuery {
        val simpleQuery =
            StringBuilder().append("SELECT * FROM showbizTable where favorite = 1 and isTvShow = 0 ")
        when (filter) {
            NEWEST -> {
                simpleQuery.append("ORDER BY releaseDate DESC")
            }
        }
        return SimpleSQLiteQuery(simpleQuery.toString())
    }

    fun getQueryFavoriteTvShows(filter: String): SimpleSQLiteQuery {
        val simpleQuery =
            StringBuilder().append("SELECT * FROM showbizTable where Favorite = 1 and isTvShow = 1 ")
        when (filter) {
            NEWEST -> {
                simpleQuery.append("ORDER BY releaseDate DESC")
            }
        }
        return SimpleSQLiteQuery(simpleQuery.toString())
    }
}