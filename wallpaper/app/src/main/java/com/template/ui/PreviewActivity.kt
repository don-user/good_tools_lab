package com.template.ui

import android.app.WallpaperManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.template.R
import com.template.databinding.ActivityPreviewBinding

class PreviewActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityPreviewBinding.inflate(layoutInflater)
    }
    private var wallpaper: Int = DEFAULT_VALUE
    private var  nameWallpaper: String = EMPTY_STRING

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        parsIntent()
        setupView()
        setWallpapers()
    }

    private fun parsIntent() {

        wallpaper = intent.getIntExtra(EXTRA_IMAGE, DEFAULT_VALUE)
        nameWallpaper = intent.getStringExtra(EXTRA_NAME) ?: EMPTY_STRING
    }

    private fun setupView() = with(binding){
        imageWallpaper.setImageResource(wallpaper)

        if(nameWallpaper.isBlank()){
            tvImageName.text = getString(R.string.app_name)
        } else tvImageName.text = nameWallpaper
    }

    private fun setWallpapers(){

        val wallpaperManager = WallpaperManager.getInstance(applicationContext)
        binding.setButton.setOnClickListener {
            try {
                wallpaperManager.setResource(wallpaper)
                showToast(TOAST_SET)
            } catch (e: Exception) {showToast(TOAST_ERROR)}
        }
    }

    private fun showToast(text: String){
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
    }

    companion object{
        private const val EXTRA_IMAGE = "extra_image"
        private const val EXTRA_NAME = "extra_name"
        private const val EMPTY_STRING = ""
        private const val DEFAULT_VALUE = 0
        private const val TOAST_SET = "Wallpaper installation was successful!"
        private const val TOAST_ERROR = "Wallpaper setup failed!"


        fun newIntentWallpaper(context: Context, wallpaper: Int, name: String): Intent {
            val intent = Intent(context, PreviewActivity::class.java)
            intent.putExtra(EXTRA_IMAGE, wallpaper)
            intent.putExtra(EXTRA_NAME, name)

            return intent
        }
    }
}
