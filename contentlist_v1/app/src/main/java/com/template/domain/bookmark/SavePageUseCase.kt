package com.template.domain.bookmark

class SavePageUseCase(private val repository: BookmarkRepository) {

    fun savePage(page: Int){
        repository.savePage(page)
    }
}