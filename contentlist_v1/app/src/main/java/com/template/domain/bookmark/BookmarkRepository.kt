package com.template.domain.bookmark

interface BookmarkRepository {

    fun getSetPage() : MutableSet<String>?

    fun savePage(page: Int)

}