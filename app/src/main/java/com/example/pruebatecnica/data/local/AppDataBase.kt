package com.example.pruebatecnica.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.pruebatecnica.data.model.MovieListModelEntity


@Database(entities = [MovieListModelEntity::class], version = 1)
abstract class AppDataBase:RoomDatabase() {

        abstract fun movieDao(): MovieDao

        companion object {
            private var INSTANCE: AppDataBase? =  null
            fun getDataBase(context: Context): AppDataBase {
                INSTANCE = INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    AppDataBase::class.java,
                    "movie_table"
                ).build()
                return INSTANCE!!
            }
            fun destroyInstance() {
                INSTANCE = null
            }
        }

}