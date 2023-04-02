package com.template.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.template.databinding.ActivityMainBinding
import com.template.domain.GetListWallpapers
import com.template.domain.WallpapersList
import com.template.ui.adapter.WallpaperAdapter
import com.template.ui.dialog.ExitDialog
import com.template.utils.Constants.NUMBER_OF_COLUMNS
import com.template.utils.Constants.URI_LICENSE


class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private var adapterWallpaper = WallpaperAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val list = GetListWallpapers.getWallpapersList(this)

        setupRecyclerView(list)
        setFloatingButton()
    }

    override fun onBackPressed() {
        ExitDialog.showDialog(this)
    }

    private fun setupRecyclerView(list: List<WallpapersList>) = with(binding.recyclerView){
        layoutManager = GridLayoutManager(this@MainActivity, NUMBER_OF_COLUMNS)
        adapter = adapterWallpaper
        adapterWallpaper.submitList(list)
        setupClickListener()
    }

    private fun setupClickListener(){
        adapterWallpaper.onImageClickListener = {
            val intent = PreviewActivity.newIntentWallpaper(this, it.imageUrl, it.name)
            startActivity(intent)
        }
    }

    private fun setFloatingButton(){
        binding.fab.setOnClickListener {
            openLinkWithBrowser()
        }
    }

    private fun openLinkWithBrowser(){
        val intentBrowser = Intent(Intent.ACTION_VIEW, Uri.parse(URI_LICENSE))
        startActivity(intentBrowser)

    }
}