package com.template.ui.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.template.base.bookmarkBase.BookmarkRepositoryImpl
import com.template.databinding.FragmentBookmarkBinding
import com.template.domain.Bookmark
import com.template.domain.bookmark.GetSavePageUseCase
import com.template.ui.adapters.AdapterBookmark
import com.template.utils.Constants.SHARED_PREFS

class BookmarkFragment : BaseFragment(), AdapterBookmark.ItemHolder.ItemClickListener {

    private lateinit var binding: FragmentBookmarkBinding

    private var adapter: AdapterBookmark? = null

    private val sharedPreferences by lazy {
        requireActivity().getSharedPreferences( SHARED_PREFS, Context.MODE_PRIVATE )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBookmarkBinding.inflate(inflater, container, false)

        val repository = BookmarkRepositoryImpl(sharedPreferences)
        setupAdapterBookmark(repository)

        return binding.root
    }

    override fun onClickItem(shopListNameItem: Bookmark) {
        val page = shopListNameItem.page - 1
        FragmentManager.setFragment(MainMenuFragment.newInstanceViewPage(page), activity as AppCompatActivity)
    }

    private fun setupAdapterBookmark(repository: BookmarkRepositoryImpl) = with(binding){
        val listBookmark = GetSavePageUseCase(repository).mapSetToList()
        adapter = AdapterBookmark(this@BookmarkFragment)
        rvBookmark.adapter = adapter
        adapter?.submitList(listBookmark)
    }

    companion object{
        fun newInstanceBookmark() = BookmarkFragment()
    }
}