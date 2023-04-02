package com.template.ui.fragments

import androidx.appcompat.app.AppCompatActivity
import com.template.R

object FragmentManager {
    var currentFrag: BaseFragment? = null


    fun setFragment(newFragment: BaseFragment, activity: AppCompatActivity) {
        activity.supportFragmentManager.beginTransaction()
            .replace(R.id.mainContainer, newFragment)
            .commit()
        currentFrag = newFragment
    }
}