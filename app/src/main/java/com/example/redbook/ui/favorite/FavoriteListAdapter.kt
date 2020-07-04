package com.example.redbook.ui.favorite

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.redbook.R
import com.example.redbook.data.model.BookData
import com.example.redbook.ui.animal.AnimaItemClickListener
import kotlinx.android.synthetic.main.item_animal.view.*


class FavoriteListAdapter (private val listener : AnimaItemClickListener) :RecyclerView.Adapter<FavoriteListAdapter.FavoriteViewHolder>() {
    inner class FavoriteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        fun populateModel(model: BookData){
            itemView.EngName.text=model.nameEng
            itemView.RusName.text=model.nameRus
            itemView.UzbName.text=model.nameUzb
            val imageResName = "picture${model.id}"
            Glide
                .with(itemView)
                .load(itemView.context.resources.getIdentifier(imageResName, "drawable", itemView.context.packageName))
                .into(itemView.ivAnimal)

            itemView.setOnClickListener{
                listener.onAnimalItemClick(model.id)
            }

        }

    }

    var models : List<BookData> = listOf()
    set(value) {
        field=value
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_animal,parent,false)
        return FavoriteViewHolder(view)
    }

    override fun getItemCount(): Int =models.size

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        holder.populateModel(models[position])
    }
}