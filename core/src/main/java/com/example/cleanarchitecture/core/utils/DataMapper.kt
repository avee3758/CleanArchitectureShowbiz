package com.example.cleanarchitecture.core.utils

import com.example.cleanarchitecture.core.data.source.local.entity.Entity
import com.example.cleanarchitecture.core.data.source.remote.response.MovieResponseList
import com.example.cleanarchitecture.core.data.source.remote.response.TvShowResponseList
import com.example.cleanarchitecture.core.domain.model.Showbiz


object DataMapper {
    fun movieResponsesToEntities(input: List<MovieResponseList>): List<Entity> {
        val movieList = ArrayList<Entity>()
        input.map {
            val movie = Entity(
                it.overview,
                it.releaseDate,
                it.id,
                it.title,
                it.posterPath,
                favorite = false,
                isTvShows = false
            )
            movieList.add(movie)
        }
        return movieList
    }

    fun tvShowResponsesToEntities(input: List<TvShowResponseList>): List<Entity> {
        val movieList = ArrayList<Entity>()
        input.map {
            val movie = Entity(
                it.overview,
                it.firstAirDate,
                it.id,
                it.name,
                it.posterPath,
                favorite = false,
                isTvShows = true
            )
            movieList.add(movie)
        }
        return movieList
    }

    fun entitiesToDomain(input: List<Entity>): List<Showbiz> {
        return input.map {
            Showbiz(
                it.overview,
                it.releaseDate,
                it.id,
                it.title,
                it.posterPath,
                favorite = it.favorite,
                isTvShows = it.isTvShows
            )
        }
    }

    fun domainToEntity(input: Showbiz): Entity {
        return Entity(
            input.overview,
            input.releaseDate,
            input.id,
            input.title,
            input.posterPath,
            favorite = input.favorite,
            isTvShows = input.isTvShows
        )
    }
}