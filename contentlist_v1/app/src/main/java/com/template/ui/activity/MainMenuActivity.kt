package com.template.ui.activity

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.template.R
import com.template.databinding.ActivityMainMenuBinding
import com.template.ui.fragments.BookmarkFragment
import com.template.ui.fragments.FragmentManager
import com.template.ui.fragments.MainMenuFragment
import com.template.utils.Constants.START_PAGE


class MainMenuActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainMenuBinding
    private var currentMenuItemId = R.id.mainMenu

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        FragmentManager.setFragment(MainMenuFragment.newInstanceViewPage(START_PAGE), this)

        setBottomNavigationListener()
    }

    private fun setBottomNavigationListener(){
        binding.menuBottomNavView.setOnItemSelectedListener {
            when(it.itemId){
                R.id.mainMenu -> {
                    currentMenuItemId = R.id.mainMenu
                    FragmentManager.setFragment(MainMenuFragment.newInstanceViewPage(START_PAGE), this)
                }
                R.id.bookmark -> {
                    currentMenuItemId = R.id.bookmark
                    FragmentManager.setFragment(BookmarkFragment.newInstanceBookmark(), this)
                }
            }
            true
        }
    }
}