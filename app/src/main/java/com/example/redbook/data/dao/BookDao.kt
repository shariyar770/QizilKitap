package com.example.redbook.data.dao

import androidx.room.Dao
import androidx.room.Query

@Dao
interface BookDao {

    @Query("SELECT * FROM  book ")

}