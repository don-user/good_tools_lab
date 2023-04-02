package com.template.ui.adapter.arcticles

import androidx.recyclerview.widget.DiffUtil
import com.template.domain.models.ListArticles
import com.template.domain.models.ListForAdapter

class ItemArticlesDiffCallBack: DiffUtil.ItemCallback<ListArticles>(){

    override fun areItemsTheSame(oldItem: ListArticles, newItem: ListArticles): Boolean {
        return oldItem.nameArticle == newItem.nameArticle
    }

    override fun areContentsTheSame(oldItem: ListArticles, newItem: ListArticles): Boolean {
        return oldItem == newItem
    }

}