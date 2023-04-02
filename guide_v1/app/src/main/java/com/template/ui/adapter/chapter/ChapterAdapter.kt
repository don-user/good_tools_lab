package com.template.ui.adapter.chapter

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.template.domain.models.ListForAdapter

class ChapterAdapter(
    private val itemClickChapter: ItemClickChapter
    ) : ListAdapter<ListForAdapter, ItemChapterHolder>(ItemChapterDiffCallBack()){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemChapterHolder {
        return ItemChapterHolder.create(parent)
    }

    override fun onBindViewHolder(chapterHolder: ItemChapterHolder, position: Int) {
            chapterHolder.setDataChapter(getItem(position), itemClickChapter)
    }

    interface ItemClickChapter{
            fun onClickItemChapter(listNameItem: ListForAdapter)
        }
    }
