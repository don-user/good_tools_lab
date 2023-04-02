package com.template.ui.adapter

import androidx.recyclerview.widget.DiffUtil
import com.template.domain.WallpapersList

class ItemWallpapersDiffCallBack: DiffUtil.ItemCallback<WallpapersList>(){

    override fun areItemsTheSame(oldItem: WallpapersList, newItem: WallpapersList): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(oldItem: WallpapersList, newItem: WallpapersList): Boolean {
        return oldItem == newItem
    }

}