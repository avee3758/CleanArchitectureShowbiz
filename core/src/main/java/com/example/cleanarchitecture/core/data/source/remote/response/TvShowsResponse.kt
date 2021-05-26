package com.example.cleanarchitecture.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class TvShowsResponse(

    @field:SerializedName("results")
    val results: List<TvShowResponseList>
)

data class TvShowResponseList(

    @field:SerializedName("first_air_date")
    val firstAirDate: String,

    @field:SerializedName("overview")
    val overview: String,

    @field:SerializedName("name")
    val name: String,

    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("poster_path")
    val posterPath: String
    )