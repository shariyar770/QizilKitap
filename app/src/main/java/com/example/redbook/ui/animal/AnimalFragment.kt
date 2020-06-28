package com.example.redbook.ui.animal

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.redbook.R
import com.example.redbook.data.BookDatabase
import com.example.redbook.data.dao.BookDao
import kotlinx.android.synthetic.main.fragment_animal.*

class AnimalFragment : Fragment(R.layout.fragment_animal) {

    private  val adapter = AnimalListAdapter()
  private  lateinit var  dao : BookDao
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
     recyclerView.adapter=adapter
        recyclerView.addItemDecoration(DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL))
        dao = BookDatabase.getInstance(requireContext()).dao()
        setData()
    }

    private fun setData(){
        adapter.models =dao.getAllAnimals()
    }
}