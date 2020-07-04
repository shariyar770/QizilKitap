package com.example.redbook.ui.animal

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.redbook.R
import com.example.redbook.data.BookDatabase
import com.example.redbook.data.dao.BookDao
import com.example.redbook.data.model.BookData
import com.example.redbook.ui.MainActivity
import com.example.redbook.ui.detail.DetailActivity
import kotlinx.android.synthetic.main.fragment_animal.*

class AnimalFragment : Fragment(R.layout.fragment_animal), AnimaItemClickListener {

    private  val adapter = AnimalListAdapter(this)
  private  lateinit var  dao : BookDao
    private lateinit var presenter: AnimalPresenter
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
     recyclerView.adapter=adapter
        recyclerView.addItemDecoration(DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL))
        val type =arguments?.getInt(MainActivity.TYPE_ID) ?: 1
        dao = BookDatabase.getInstance(requireContext()).dao()

          presenter = AnimalPresenter(dao,this)
          presenter.getAllAnimals(type)

        etSearch.addTextChangedListener{
            val result : List <BookData> = dao.searchAnimalByName(type, "${it.toString()}%")
            adapter.models=result
        }
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