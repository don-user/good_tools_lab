package com.template.base.viewPagerBase

import android.content.Context
import com.template.domain.viewPager.ViewPagerRepository
import com.template.utils.Constants
import com.template.utils.Constants.PAGE

class ViewPagerRepositoryImpl(private val context: Context) : ViewPagerRepository{

    override fun getAssetsList(): List<String> {
        val asset = context.assets
        val assetsList = asset?.list("")?.toList()?.filter {
            it.contains(PAGE)
        } ?: listOf()
        val pageList = mutableListOf<String>()
        for (i in assetsList){
            pageList.add(Constants.ASSETS + i)
        }
        return pageList
    }
}