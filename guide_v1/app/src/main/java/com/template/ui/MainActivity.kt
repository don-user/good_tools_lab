package com.template.ui

import android.os.Bundle
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import com.template.R
import com.template.databinding.ActivityMainBinding
import com.template.domain.models.Chapters
import com.template.ui.fragments.*
import com.template.ui.fragments.FragmentManager.launchFragment
import com.template.utils.Constants.HOME

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    private lateinit var actionBarToggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        startHomActivity()
        setupDrawerMenu()
        navigationView()

        binding.mainNavigationView.menu.getItem(0).isChecked

    }

    override fun onSupportNavigateUp(): Boolean {
        binding.mainDrawer.openDrawer(binding.mainNavigationView)
        closeDrawerMenu()
        return true
    }

    override fun onBackPressed() {
        if (!this.binding.mainDrawer.isDrawerOpen(GravityCompat.START)) {
            super.onBackPressed()
        } else closeDrawerMenu()
    }



    private fun setupDrawerMenu(){
        actionBarToggle = ActionBarDrawerToggle(
            this,
            binding.mainDrawer,
            0,
            0
        )
        binding.mainDrawer.addDrawerListener(actionBarToggle)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        actionBarToggle.syncState()
    }

    private fun startHomActivity(){
        val fragment = HomeFragment.newInstanceHome()
        launchFragment(fragment, this, HOME)
    }

    private fun navigationView(){
        binding.mainNavigationView.setNavigationItemSelectedListener {
            when(it.itemId){

                R.id.oneFragment -> {
                    val fragment = OneFragment.newInstanceOne()
                    launchFragment(fragment, this, Chapters.ONE.nameChapter)
                    closeDrawerMenu()
                    true
                }
                R.id.twoFragment -> {
                    val fragment = TwoFragment.newInstanceTwo()
                    launchFragment(fragment, this, Chapters.TWO.nameChapter)
                    closeDrawerMenu()
                    true
                }
                R.id.threeFragment -> {
                    val fragment = ThreeFragment.newInstanceThree()
                    launchFragment(fragment, this, Chapters.THREE.nameChapter)
                    closeDrawerMenu()
                    true
                }
                R.id.fourFragment -> {
                    val fragment = FourFragment.newInstanceFour()
                    launchFragment(fragment, this, Chapters.FOUR.nameChapter)
                    closeDrawerMenu()
                    true
                }
                R.id.fiveFragment -> {
                    val fragment = FiveFragment.newInstanceFive()
                    FragmentManager.launchFragment(fragment, this, Chapters.FIVE.nameChapter)
                    closeDrawerMenu()
                    true
                }
                R.id.id_exit -> {
                    finish()
                    true
                }
                else -> {
                    false
                }
            }
        }
    }

    private fun closeDrawerMenu(){
        if (this.binding.mainDrawer.isDrawerOpen(GravityCompat.START)) {
            this.binding.mainDrawer.closeDrawer(GravityCompat.START)
        }
    }
}