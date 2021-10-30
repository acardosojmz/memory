package dev.cardoso.memory.presentation.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import dagger.hilt.EntryPoint
import dagger.hilt.android.AndroidEntryPoint
import dev.cardoso.memory.databinding.ActivityMainBinding
import dev.cardoso.memory.presentation.viewmodel.MemoryViewModel
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val memoryViewModel: MemoryViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewModelProvider(this)[MemoryViewModel::class.java]
        memoryViewModel.loadCatalogImages()
        memoryViewModel.loadImageViews(asociateImages())
        memoryViewModel.startGame()

    }

    fun asociateImages(): ArrayList<ImageView>{
        val images=ArrayList<ImageView>()
        images.add(binding.imgMemory0)
        images.add(binding.imgMemory1)
        images.add(binding.imgMemory2)
        images.add(binding.imgMemory3)
        images.add(binding.imgMemory4)
        images.add(binding.imgMemory5)
        images.add(binding.imgMemory6)
        images.add(binding.imgMemory7)
        images.add(binding.imgMemory8)
        images.add(binding.imgMemory9)
        images.add(binding.imgMemory10)
        images.add(binding.imgMemory11)
        return  images
    }
}