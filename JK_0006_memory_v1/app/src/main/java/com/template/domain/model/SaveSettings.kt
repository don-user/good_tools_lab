package com.template.domain.model

data class SaveSettings(
    var emojiListBinding: List<GameSettings>,
    val modeLvl: Level,
    var currentTime: Long = 0L
)
