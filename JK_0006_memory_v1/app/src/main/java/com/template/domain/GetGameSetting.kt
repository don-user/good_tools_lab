package com.template.domain

import android.content.Context
import com.template.domain.model.EmojiBinding
import com.template.domain.model.Level

class GetGameSetting(private val context: Context) {

    private var autoIncID = 0

    fun getGameList(numberEmoji: Int?) : MutableList<EmojiBinding>{
        val a: MutableList<Int> = getEmojiLis().take(numberEmoji!!).toMutableList()
        a.addAll(a)
        a.shuffled().toList()
        val listGame = mutableListOf<EmojiBinding>()
        for (i in a){
            listGame.add(
                EmojiBinding(id = ++autoIncID, textViewBinding = null, emoji = i)
            )
        }
        return listGame
    }

    private fun getEmojiLis() : List<Int>{

        val list = mutableListOf<Int>()

        for(i in 1..Level.MODE_8X8.numberCard) {

            val emojiName = context.resources.getIdentifier(
                BASE_EMOJI + i, STRING, context.packageName
            )
            list.add(emojiName)
        }
        return list
    }


    companion object{
        private const val BASE_EMOJI = "emoji"
        private const val STRING = "string"
    }
}