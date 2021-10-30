package dev.cardoso.memory.presentation.viewmodel

import android.app.Application
import android.content.Context
import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.lifecycle.ViewModel
import dagger.hilt.InstallIn
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.cardoso.memory.R
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject


@HiltViewModel
class MemoryViewModel @Inject constructor( application: Application) : ViewModel() {
    private val catalogImages: MutableList<Int> = mutableListOf()
    private val selectedImagesForPlay: MutableList<Int> = mutableListOf()
    private val imageViews: MutableList<ImageView> = mutableListOf()
    private var clicks=0

    private var previousImage =  ImageView(application.baseContext)
    private var currentImage =  ImageView(application.baseContext)

    init {
        loadCatalogImages()
    }

    fun loadCatalogImages(){
        this.catalogImages.add(R.drawable.apple)
        this.catalogImages.add(R.drawable.banana)
        this.catalogImages.add(R.drawable.broccoli)
        this.catalogImages.add(R.drawable.cantaloupe)
        this.catalogImages.add(R.drawable.carrot)
        this.catalogImages.add(R.drawable.cherry)
        this.catalogImages.add(R.drawable.chillies)
        this.catalogImages.add(R.drawable.eggplant)
        this.catalogImages.add(R.drawable.grape)
        this.catalogImages.add(R.drawable.kiwi)
        this.catalogImages.add(R.drawable.lettuce)
        this.catalogImages.add(R.drawable.morron)
        this.catalogImages.add(R.drawable.orange)
        this.catalogImages.add(R.drawable.pineapple)
        this.catalogImages.add(R.drawable.pitahaya)
        this.catalogImages.add(R.drawable.pumpkin)
        this.catalogImages.add(R.drawable.strawberry)
        this.catalogImages.add(R.drawable.tomato)
        this.catalogImages.add(R.drawable.watermelon)
    }

    fun loadImageViews(images:MutableList<ImageView>){
        this.imageViews.addAll(images)
    }

    fun suffleImages() {
        this.catalogImages.shuffle()
    }

    fun startGame(){
        clicks=0
        suffleImages()
        val subListImages = this.catalogImages.slice(0..5)
        Log.e("SUBLIST", subListImages.toString())
        selectedImagesForPlay.clear()
        selectedImagesForPlay.addAll(subListImages)
        //--- duplicate elements
        selectedImagesForPlay.addAll(subListImages)
        //--- shuffle
        selectedImagesForPlay.shuffle()

        asociateImageViewWithDrawables()
    }

    private fun asociateImageViewWithDrawables(){
        var index = 0
        this.imageViews.forEach { it ->
            it.setImageResource(R.mipmap.ic_launcher)
            it.tag = this.selectedImagesForPlay[index]
            it.setOnClickListener { object : View.OnClickListener {
                    override fun onClick(v: View) {

                    }

                }
            }

            it.setOnClickListener { view->
                val imageView = view as ImageView
                analyzeMove(imageView,Integer.parseInt(it.tag.toString()))
            }
            index++
        }
    }
    fun analyzeMove(imageView: ImageView, image:Int) {
        clicks++
        currentImage = imageView
        imageView.setImageResource(image)
        if (clicks%2==0) {
            if (previousImage.tag == currentImage.tag
            ) {
                imageView.isEnabled = false
                previousImage.isEnabled = false
                previousImage = imageView
            }
            else {
                imageView.setImageResource(R.mipmap.ic_launcher)
                previousImage = imageView

            }
        }

    }

}