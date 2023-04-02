package com.template.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.template.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding by lazy{
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        buttonClick()
    }

    private fun buttonClick(){

        binding.button.setOnClickListener {
            val intent = Intent(this, GameLevelActivity::class.java)
            startActivity(intent)
        }

    }
}