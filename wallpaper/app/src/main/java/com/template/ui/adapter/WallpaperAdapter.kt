package com.template.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.template.databinding.ItemWallpaperBinding
import com.template.domain.WallpapersList

class WallpaperAdapter():
    ListAdapter<WallpapersList, ItemWallpapersHolder>(ItemWallpapersDiffCallBack()){

    var onImageClickListener: ((WallpapersList) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemWallpapersHolder {
        val binding = ItemWallpaperBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ItemWallpapersHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemWallpapersHolder, position: Int) {
        val image = getItem(position)

        holder.binding.image.setImageResource(image.imageUrl)

        holder.binding.root.setOnClickListener {
            onImageClickListener?.invoke(image)
        }
    }
}
