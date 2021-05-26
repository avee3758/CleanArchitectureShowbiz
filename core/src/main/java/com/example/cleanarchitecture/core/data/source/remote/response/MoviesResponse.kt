package com.example.cleanarchitecture.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class MoviesResponse(

    @field:SerializedName("results")
    val results: List<MovieResponseList>
)

data class MovieResponseList(

    @field:SerializedName("overview")
    val overview: String,

    @field:SerializedName("release_date")
    val releaseDate: String,

    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("title")
    val title: String,

    @field:SerializedName("poster_path")
    val posterPath: String
)
