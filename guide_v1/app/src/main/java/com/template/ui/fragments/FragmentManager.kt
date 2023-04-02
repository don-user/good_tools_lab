package com.template.ui.fragments

import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.template.R

object FragmentManager {

    fun launchFragment(fragment: Fragment, activity: AppCompatActivity, name: String){
        activity.supportFragmentManager.beginTransaction()
            .replace(R.id.container, fragment)
            .addToBackStack(name)
            .commit()
    }

    fun backPressFragment(activity: AppCompatActivity, name: String?){
        val callback = object : OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                activity.supportFragmentManager.popBackStack(
                    name,
                    0
                )
            }
        }
        activity.onBackPressedDispatcher.addCallback(activity, callback)
    }
}