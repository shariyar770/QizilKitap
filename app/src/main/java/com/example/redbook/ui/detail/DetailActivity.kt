package com.example.redbook.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.bumptech.glide.Glide
import com.example.redbook.R
import com.example.redbook.data.BookDatabase
import com.example.redbook.data.dao.BookDao
import com.example.redbook.data.model.BookData
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.item_animal.*
import kotlinx.android.synthetic.main.item_animal.view.*

class DetailActivity : AppCompatActivity() {

    companion object {
        const val ANIMAL_ID = "animalId"
    }

    private var animalId : Int  = 0
    private lateinit var currentAnimal : BookData
    private lateinit var  dao : BookDao
    private  var menuItem: MenuItem?= null

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setTitle("details")
        dao = BookDatabase.getInstance(this).dao()

        animalId = intent.getIntExtra(ANIMAL_ID,0)
        currentAnimal= dao.getAnimalByID(animalId)


        tvStatusContent.text=currentAnimal.status
        tvHabitatContent.text=currentAnimal.habitat
        tvBreedingContent.text=currentAnimal.breeding
        tvLifestyleContent.text=currentAnimal.lifestyle
        tvLimitingFactorsContent.text=currentAnimal.limitingFactors
        tvPropagationContent.text=currentAnimal.propagation
        tvQuantityContent.text=currentAnimal.quantity
        tvSecurityContent.text=currentAnimal.security

        Glide
            .with(this)
            .load(resources.getIdentifier("picture${animalId}", "drawable",packageName))
            .into(ivDetail)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_detail,menu)

        menuItem= menu?.findItem(R.id.item_bookmark)
        setFavoriteIcon()
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId){
            android.R.id.home -> finish()
            R.id.item_bookmark -> setFavorite()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setFavorite() {
        if(currentAnimal.isFavorite==null) currentAnimal.isFavorite=1
        else currentAnimal.isFavorite=1- currentAnimal.isFavorite!!
        setFavoriteIcon()
        dao.updateAnimal(currentAnimal)
    }

    private  fun setFavoriteIcon(){
        if(currentAnimal.isFavorite == 1){
           menuItem?.setIcon(R.drawable.ic_bookmark_black_24dp)
        }
        else{
            menuItem?.setIcon(R.drawable.ic_bookmark_border_black_24dp)
        }
    }
}
