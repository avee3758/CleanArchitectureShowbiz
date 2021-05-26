package com.example.cleanarchitecture.core.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.cleanarchitecture.core.data.source.local.entity.Entity

@Database(entities = [Entity::class], version = 1, exportSchema = false)
abstract class LocalDatabase : RoomDatabase() {

    abstract fun showbizDao(): AppDao

}