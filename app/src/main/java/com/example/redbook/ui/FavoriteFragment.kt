package com.example.redbook.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.redbook.R
import com.example.redbook.data.BookDatabase
import com.example.redbook.data.dao.BookDao
import com.example.redbook.data.model.BookData
import com.example.redbook.ui.animal.AnimaItemClickListener
import com.example.redbook.ui.animal.AnimalPresenter
import com.example.redbook.ui.detail.DetailActivity
import com.example.redbook.ui.favorite.FavoriteListAdapter
import com.example.redbook.ui.favorite.FavoritePresenter
import kotlinx.android.synthetic.main.fragment_favorite.*
import kotlin.reflect.typeOf

class FavoriteFragment() : Fragment(R.layout.fragment_favorite), AnimaItemClickListener {
    private  lateinit var  dao : BookDao
   private var adapter : FavoriteListAdapter = FavoriteListAdapter(this)

    private lateinit var presenter: FavoritePresenter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        favoriteList.adapter=adapter
        favoriteList.addItemDecoration(DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL))
        dao = BookDatabase.getInstance(requireContext()).dao()

        presenter = FavoritePresenter(dao,this)
        presenter.getFavorites()
    }

    override fun onStart() {
        super.onStart()
        presenter = FavoritePresenter(dao,this)
        presenter.getFavorites()
    }

    fun setData(models:  List<BookData>){
        adapter.models=models
    }

    override fun onAnimalItemClick(id: Int) {
        val mIntent = Intent(requireActivity(), DetailActivity::class.java)
        mIntent.putExtra(DetailActivity.ANIMAL_ID, id)
        startActivity(mIntent)
    }
}