package com.template.domain.usecase

import com.template.domain.GameRepository
import com.template.domain.model.QuestionAndAnswer

class QuestionAnswerUseCase(private val repository: GameRepository) {

    private fun getQuestionAndAnswerList():
            List<QuestionAndAnswer> = repository.questionAndAnswer()

    fun getQuestionAndAnswer() : QuestionAndAnswer? {
        val answerList = getQuestionAndAnswerList()
        val answerMutList: MutableList<QuestionAndAnswer> = answerList.toMutableList()
        val index = 0
        val answer: QuestionAndAnswer?

        if (index <= answerMutList.size){
            answer = answerMutList.first()
            answerMutList.removeAt(0)
        }else answer = null

        return answer
    }
}