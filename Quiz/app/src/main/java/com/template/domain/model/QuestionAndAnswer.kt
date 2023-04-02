package com.template.domain.model

data class QuestionAndAnswer(
    val question: String,
    val rightAnswer: String,
    val optionAnswer: List<String>
)
