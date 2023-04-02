package com.template.utils

import android.widget.TextView
import com.template.databinding.ActivityGameBinding
import com.template.domain.model.EmojiBinding
import com.template.domain.model.GameSettings
import com.template.utils.Constants.UNKNOWN_NUMBER
import com.template.utils.Constants.UNKNOWN_TEXT_VIEW

class Mapper (private val binding: ActivityGameBinding){

    fun mapEmojiBindingToGameSettings(list: List<EmojiBinding>): List<GameSettings>{
        val listGame = list.map {
            GameSettings(
                it.id!!,
                textViewNumber = textViewIntHelper(it.textViewBinding!!),
                it.emoji!!,
                it.status
            )
        }
        return listGame
    }

    fun mapGameSettingsToEmojiBindings(list: List<GameSettings>) : List<EmojiBinding>{
        val listSettings = list.map {
            EmojiBinding(
                it.id,
                textViewBinding = textViewHelper(it.textViewNumber),
                it.emoji,
                it.status
            )
        }
        return listSettings
    }

    private fun textViewIntHelper(textView: TextView): Int{
        return when(textView){
            binding.tvEmoji1 -> 1
            binding.tvEmoji2 -> 2
            binding.tvEmoji3 -> 3
            binding.tvEmoji4 -> 4
            binding.tvEmoji5 -> 5
            binding.tvEmoji6 -> 6
            binding.tvEmoji7 -> 7
            binding.tvEmoji8 -> 8
            binding.tvEmoji9 -> 9
            binding.tvEmoji10 -> 10
            binding.tvEmoji11 -> 11
            binding.tvEmoji12 -> 12
            binding.tvEmoji13 -> 13
            binding.tvEmoji14 -> 14
            binding.tvEmoji15 -> 15
            binding.tvEmoji16 -> 16

            else -> throw RuntimeException(UNKNOWN_TEXT_VIEW)
        }
    }
    private fun textViewHelper(textViewNumber: Int): TextView{
        return when(textViewNumber){
            1 -> binding.tvEmoji1
            2 -> binding.tvEmoji2
            3 -> binding.tvEmoji3
            4 -> binding.tvEmoji4
            5 -> binding.tvEmoji5
            6 -> binding.tvEmoji6
            7 -> binding.tvEmoji7
            8 -> binding.tvEmoji8
            9 -> binding.tvEmoji9
            10 -> binding.tvEmoji10
            11 -> binding.tvEmoji11
            12 -> binding.tvEmoji12
            13 -> binding.tvEmoji13
            14 -> binding.tvEmoji14
            15 -> binding.tvEmoji15
            16 -> binding.tvEmoji16

            else -> throw RuntimeException(UNKNOWN_NUMBER)
        }
    }
}