package com.example.cleanarchitecture.core.di

import androidx.room.Room
import com.example.cleanarchitecture.core.data.source.local.room.LocalDatabase
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val LocalDbModule = module {
    factory { get<LocalDatabase>().showbizDao() }
    single {
        val passphrase: ByteArray = SQLiteDatabase.getBytes("codext".toCharArray())
        val factory = SupportFactory(passphrase)
        Room.databaseBuilder(
            androidContext(),
            LocalDatabase::class.java, "showbiz.db"
        ).fallbackToDestructiveMigration()
            .openHelperFactory(factory)
            .build()
    }
}