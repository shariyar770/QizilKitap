package com.example.redbook.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.redbook.data.dao.BookDao
import com.example.redbook.data.model.BookData


@Database(entities = [BookData::class], version = 3)

abstract class BookDatabase : RoomDatabase() {

    companion object {
        private  lateinit var INSTANCE: BookDatabase

        fun getInstance(context: Context): BookDatabase =
         Room.databaseBuilder(
             context,
             BookDatabase::class.java,
             "book-database.db"
         )
             .createFromAsset("book-database.db")
             .allowMainThreadQueries()
             .build()
    }

    abstract fun dao() : BookDao
}