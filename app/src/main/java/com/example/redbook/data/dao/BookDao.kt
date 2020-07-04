package com.example.redbook.data.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Update
import com.example.redbook.data.model.BookData

@Dao
interface BookDao {
    @Query("SELECT * FROM  book WHERE type=:type")
    fun getAllAnimals(type: Int) : List<BookData>

    @Query("Select * FROM book WHERE id=:id")
    fun getAnimalByID(id: Int) : BookData

    @Query("Select * FROM book WHERE type= :type and nameEng like :word")
    fun searchAnimalByName(type: Int , word : String) : List<BookData>

    @Update
    fun updateAnimal(animal: BookData)

    @Query("Select * FROM book WHERE isFavorite=1")
    fun getFavorites() : List <BookData>

}

