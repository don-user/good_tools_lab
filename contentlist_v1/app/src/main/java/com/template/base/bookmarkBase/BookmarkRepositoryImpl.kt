package com.template.base.bookmarkBase

import android.content.SharedPreferences
import android.util.Log
import com.template.domain.bookmark.BookmarkRepository
import com.template.utils.Constants.PAGE

class BookmarkRepositoryImpl(private val sharedPref: SharedPreferences): BookmarkRepository {


    override fun getSetPage() : MutableSet<String>? {
        return sharedPref.getStringSet(PAGE, setOf())
    }

    override fun savePage(page: Int) {
        val newPage = page + 1
        val setList = sharedPref.getStringSet(PAGE, setOf())
        val newSetList: MutableSet<String> = setList?.toMutableSet() ?: mutableSetOf()
            newSetList.add(newPage.toString())

        sharedPref.edit().putStringSet(PAGE, newSetList).apply()
    }
}