package com.template.ui.adapter.chapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.template.R
import com.template.databinding.ItemCardBinding
import com.template.domain.models.ListForAdapter

class ItemChapterHolder(view: View): RecyclerView.ViewHolder(view) {
    private val binding = ItemCardBinding.bind(view)

    fun setDataChapter(
        listNameItem: ListForAdapter, chapter: ChapterAdapter.ItemClickChapter
    ) = with(binding) {

        tvName.text = listNameItem.nameChapter
        itemView.setOnClickListener {
            chapter.onClickItemChapter(listNameItem)
        }
    }

    companion object {
        fun create(parent: ViewGroup): ItemChapterHolder {
            return ItemChapterHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_card, parent, false)
            )
        }
    }
}