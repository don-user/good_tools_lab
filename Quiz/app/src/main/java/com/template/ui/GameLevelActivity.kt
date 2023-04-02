package com.template.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.template.databinding.ActivityGameLevelBinding
import com.template.domain.model.Level
import com.template.utils.Constants.EXTRA

class GameLevelActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityGameLevelBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        chooseLevel()
    }



    private fun chooseLevel() = with(binding){
        buttonEasy.setOnClickListener {
            startIntent(Level.EASY.toString())
        }

        buttonMedium.setOnClickListener {
            startIntent(Level.MEDIUM.toString())
        }

        buttonHard.setOnClickListener {
            startIntent(Level.HARD.toString())
        }
    }

    private fun startIntent(level: String){
        val intent = Intent(this, GameActivity::class.java)
        intent.putExtra(EXTRA, level)
        startActivity(intent)
    }



}