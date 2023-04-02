package com.template.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.template.R
import com.template.databinding.BookmarkerItemBinding
import com.template.domain.Bookmark

class AdapterBookmark(
    private val itemClickListener: ItemHolder.ItemClickListener
    ) : ListAdapter<Bookmark, AdapterBookmark.ItemHolder>(ItemComparator()){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        return ItemHolder.create(parent)
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        holder.setData(getItem(position), itemClickListener)
    }

    class ItemHolder(view: View): RecyclerView.ViewHolder(view) {
        private val binding = BookmarkerItemBinding.bind(view)
        fun setData(listNameItem: Bookmark, listener: ItemClickListener) = with(binding) {

            imageView.setImageResource(R.drawable.bookmark_black)
            tvDes.text = listNameItem.description
            tvPage.text = listNameItem.page.toString()
            itemView.setOnClickListener {
                listener.onClickItem(listNameItem)
            }
        }

        interface ItemClickListener{
            fun onClickItem(shopListNameItem: Bookmark)

        }

        companion object {
            fun create(parent: ViewGroup): ItemHolder {
                return ItemHolder(
                    LayoutInflater.from(parent.context)
                        .inflate(R.layout.bookmarker_item, parent, false)
                )
            }
        }
    }
}