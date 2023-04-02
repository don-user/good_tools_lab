package com.template.domain

import android.content.Context
import com.template.utils.Constants
import com.template.utils.Constants.DRAWABLE
import com.template.utils.Constants.NUMBER_OF_IMAGE
import com.template.utils.Constants.START_NUMBER

object GetListWallpapers {

    fun getWallpapersList(context: Context) : List<WallpapersList>{

        val list = mutableListOf<WallpapersList>()

        for(i in START_NUMBER..NUMBER_OF_IMAGE) {
            val name = Constants.BASE_IMAGE_NAME + i
            val imageUrl = context.resources.getIdentifier(
                name, DRAWABLE, context.packageName
            )
            list.add(WallpapersList(name, imageUrl))
        }
        return list
    }
}