package com.template.ui.adapter.arcticles

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.template.domain.models.ListArticles
import com.template.ui.fragments.OneFragment
import com.template.ui.fragments.ThreeFragment

class ArticlesAdapter(
    private val itemClickArticle: ItemClickArticle
    ) : ListAdapter<ListArticles, ItemArticlesHolder>(ItemArticlesDiffCallBack()){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemArticlesHolder {
        return ItemArticlesHolder.create(parent)
    }

    override fun onBindViewHolder(holder: ItemArticlesHolder, position: Int) {
        holder.setDataArticles(getItem(position), itemClickArticle)
    }

    interface ItemClickArticle{
        fun onClickItemArticle(listNameItem: ListArticles)
    }

}
