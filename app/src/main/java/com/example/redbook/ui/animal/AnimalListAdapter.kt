package com.example.redbook.ui.animal

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.redbook.R
import com.example.redbook.data.model.BookData
import kotlinx.android.synthetic.main.item_animal.view.*

class AnimalListAdapter (private val listener : AnimaItemClickListener) : RecyclerView.Adapter<AnimalListAdapter.AnimalListViewHolder>() {


    var models: List<BookData> = listOf()
    set(value){
        field=value
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimalListViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_animal,parent,false)
        return AnimalListViewHolder(itemView)
    }

    override fun getItemCount(): Int = models.size

    override fun onBindViewHolder(holder: AnimalListViewHolder, position: Int) {
        holder.populateModel(models[position])
    }

      inner class AnimalListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
          fun populateModel (animal: BookData){
            itemView.UzbName.text = animal.nameUzb
            itemView.RusName.text = animal.nameRus
            itemView.EngName.text = animal.nameEng
              val imageResName = "picture${animal.id}"
              Glide
                  .with(itemView)
                  .load(itemView.context.resources.getIdentifier(imageResName, "drawable", itemView.context.packageName))
                  .into(itemView.ivAnimal)

              itemView.setOnClickListener{
                  listener.onAnimalItemClick(animal.id)
              }
          }
      }

}