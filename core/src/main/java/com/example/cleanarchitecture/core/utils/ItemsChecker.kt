package com.example.cleanarchitecture.core.utils

import androidx.annotation.Nullable
import androidx.recyclerview.widget.DiffUtil
import com.example.cleanarchitecture.core.domain.model.Showbiz


class ItemsChecker(private val oldList: List<Showbiz>, private val newList: List<Showbiz>) :
    DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }

    override fun areContentsTheSame(oldPosition: Int, newPosition: Int): Boolean {
        val (overview,
            releaseDate,
            _,
            title,
            posterPath,
            favorite,
            isTvShows) = oldList[oldPosition]
        val (overview1,
            releaseDate1,
            _,
            title1,
            posterPath1,
            favorite1,
            isTvShows1) = newList[newPosition]

        return overview == overview1
                && releaseDate == releaseDate1
                && title == title1
                && posterPath == posterPath1
                && favorite == favorite1
                && isTvShows == isTvShows1
    }

    @Nullable
    override fun getChangePayload(oldPosition: Int, newPosition: Int): Any? {
        return super.getChangePayload(oldPosition, newPosition)
    }
}