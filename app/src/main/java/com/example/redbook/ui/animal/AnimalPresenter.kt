package com.example.redbook.ui.animal

import androidx.fragment.app.Fragment
import com.example.redbook.data.dao.BookDao

class AnimalPresenter(private val dao : BookDao, private val view : AnimalFragment) {

    fun getAllAnimals(type: Int){
        view.setData(dao.getAllAnimals(type))  
    }
}