package com.template.ui.adapters

import androidx.recyclerview.widget.DiffUtil
import com.template.domain.Bookmark

class ItemComparator: DiffUtil.ItemCallback<Bookmark>(){
    override fun areItemsTheSame(oldItem: Bookmark, newItem: Bookmark): Boolean {
        return oldItem.page == newItem.page
    }

    override fun areContentsTheSame(oldItem: Bookmark, newItem: Bookmark): Boolean {
        return oldItem == newItem
    }

}