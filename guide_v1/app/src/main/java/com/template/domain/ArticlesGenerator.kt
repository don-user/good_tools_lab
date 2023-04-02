package com.template.domain

import android.content.Context
import com.template.domain.models.Chapters
import com.template.domain.models.ListArticles
import com.template.domain.models.ListForAdapter
import com.template.utils.Constants.EMPTY

class ArticlesGenerator(private val context: Context) {

    val forAdapterList = getListForAdapterFormAssets()

    fun getListForAdapter(chapters: Chapters) : List<ListArticles>{
        return  forAdapterList.filter {
            it.nameChapter == chapters.nameChapter
        }.first().listArticles
    }

    private fun getListForAdapterFormAssets() : List<ListForAdapter>{

        val listForAdapter = mutableListOf<ListForAdapter>()
        val chapterList = enumValues<Chapters>()

        for (chapters in chapterList){
            val articlesList = mutableListOf<ListArticles>()
            val listArticles = getListFilesName(chapters.nameChapter)

            for (i in listArticles){
                val articleText = context.assets.open(i).bufferedReader().use { it.readText() }
                articlesList.add(ListArticles(i, articleText))

            }
            listForAdapter.add(ListForAdapter(chapters.nameChapter, articlesList))
        }
        return listForAdapter
    }

    private fun getListFilesName (name: String) = context.assets.list(EMPTY)?.toList()?.filter {
        it.contains(name)
    } ?: listOf()
}

