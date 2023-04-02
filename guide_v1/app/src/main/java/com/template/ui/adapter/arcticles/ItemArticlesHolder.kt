package com.template.ui.adapter.arcticles

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.template.R
import com.template.databinding.ItemCardBinding
import com.template.domain.models.ListArticles
import com.template.domain.models.ListForAdapter
import com.template.ui.adapter.chapter.ChapterAdapter

class ItemArticlesHolder(view: View): RecyclerView.ViewHolder(view) {
    private val binding = ItemCardBinding.bind(view)


    fun setDataArticles(
        listNameItem: ListArticles, listener: ArticlesAdapter.ItemClickArticle
    ) = with(binding) {

        tvName.text = listNameItem.nameArticle
        itemView.setOnClickListener {
            listener.onClickItemArticle(listNameItem)
        }
    }

    companion object {
        fun create(parent: ViewGroup): ItemArticlesHolder {
            return ItemArticlesHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_card, parent, false)
            )
        }
    }
}