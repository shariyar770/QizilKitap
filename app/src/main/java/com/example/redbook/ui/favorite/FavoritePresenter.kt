package com.example.redbook.ui.favorite

import com.example.redbook.data.dao.BookDao
import com.example.redbook.ui.FavoriteFragment
import com.example.redbook.ui.animal.AnimalFragment

class FavoritePresenter(private val dao : BookDao, private val view : FavoriteFragment) {

    fun getFavorites(){
        view.setData(dao.getFavorites())
    }

}