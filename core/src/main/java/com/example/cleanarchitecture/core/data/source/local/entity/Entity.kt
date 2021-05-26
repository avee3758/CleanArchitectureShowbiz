package com.example.cleanarchitecture.core.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "showbizTable")
data class Entity(

    @ColumnInfo(name = "overview")
    var overview: String,

    @ColumnInfo(name = "releaseDate")
    var releaseDate: String,

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    var id: Int,

    @ColumnInfo(name = "title")
    var title: String,

    @ColumnInfo(name = "posterPath")
    var posterPath: String,

    @ColumnInfo(name = "favorite")
    var favorite: Boolean = false,

    @ColumnInfo(name = "isTvShow")
    var isTvShows: Boolean = false
)
