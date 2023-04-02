package com.template.ui.adapters

import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.template.ui.fragments.MainMenuFragment
import com.template.ui.fragments.PageFragment
import com.template.utils.Constants.ARG_OBJECT

class AdapterViewPager(fragment: MainMenuFragment, private val assetsList: List<String>) : FragmentStateAdapter(fragment) {

    override fun getItemCount() : Int{
        return assetsList.size
    }

    override fun createFragment(position: Int): Fragment {
        var index = position
        val fragment = PageFragment()
        fragment.arguments = bundleOf(
            ARG_OBJECT to assetsList[index++]
        )
        return fragment
    }

}