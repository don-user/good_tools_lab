package com.template.data

import com.template.data.GameMapper.mapBaseToQuestion
import com.template.domain.GameRepository
import com.template.domain.model.QuestionAndAnswer


object GameRepositoryImpl: GameRepository {

    override fun questionAndAnswer() : List<QuestionAndAnswer> {

        var count = 0
        val questionAndAnswerList = QuestionAndAnswerBase.values().toList()
        val newList = mutableListOf<QuestionAndAnswer>()

        while (count != questionAndAnswerList.size) {
            newList.add(mapBaseToQuestion(questionAndAnswerList[count]))
            count++

        }
        return newList.shuffled()
    }

}