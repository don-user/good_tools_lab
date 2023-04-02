package com.template.domain.model

import android.widget.TextView

data class EmojiBinding(
    var id: Int?,
    var textViewBinding: TextView?,
    var emoji: Int?,
    var status: Boolean = true
)
