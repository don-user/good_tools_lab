package com.template.ui.adapter.chapter

import androidx.recyclerview.widget.DiffUtil
import com.template.domain.models.ListForAdapter

class ItemChapterDiffCallBack: DiffUtil.ItemCallback<ListForAdapter>(){
    override fun areItemsTheSame(oldItem: ListForAdapter, newItem: ListForAdapter): Boolean {
        return oldItem.nameChapter == newItem.nameChapter
    }

    override fun areContentsTheSame(oldItem: ListForAdapter, newItem: ListForAdapter): Boolean {
        return oldItem == newItem
    }

}