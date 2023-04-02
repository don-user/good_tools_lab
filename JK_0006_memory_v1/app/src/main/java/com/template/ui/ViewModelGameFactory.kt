package com.template.ui

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.template.databinding.ActivityGameBinding
import com.template.domain.model.EmojiBinding

class ViewModelGameFactory(
    private val application: Application,
    private val emojiBinding: List<EmojiBinding>,
    private val binding: ActivityGameBinding
    ): ViewModelProvider.AndroidViewModelFactory(application) {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ViewModelGame(application, emojiBinding, binding) as T
    }
}