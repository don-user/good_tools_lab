package com.template.domain.bookmark

import com.template.domain.Bookmark
import com.template.utils.Constants.PAGE_NUMBER

class GetSavePageUseCase (private val repository: BookmarkRepository){

    private fun getSetPage() : MutableSet<String>?{
        return repository.getSetPage()
    }

    fun mapSetToList() : MutableList<Bookmark>{
        val set = getSetPage()
        val listBookmark = mutableListOf<Bookmark>()
        if (set != null) {
            for (i in set) {
                listBookmark.add(Bookmark(i.toInt(), PAGE_NUMBER))
            }
        }
        return listBookmark
    }
}