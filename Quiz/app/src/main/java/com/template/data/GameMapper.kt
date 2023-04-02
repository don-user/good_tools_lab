package com.template.data

import com.template.domain.model.QuestionAndAnswer

object GameMapper {

    fun mapBaseToQuestion(question: QuestionAndAnswerBase) = QuestionAndAnswer(
        question = question.name,
        rightAnswer = question.rightAnswer,
        optionAnswer = listOf(
            question.rightAnswer,
            question.wrong1Answer,
            question.wrong2Answer,
            question.wrong3Answer
        )
    )

}