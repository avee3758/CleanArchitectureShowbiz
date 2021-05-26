package com.example.cleanarchitecture.core.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Showbiz(
    var overview: String,
    var releaseDate: String,
    var id: Int,
    var title: String,
    var posterPath: String,
    var favorite: Boolean = false,
    var isTvShows: Boolean = false
) : Parcelable