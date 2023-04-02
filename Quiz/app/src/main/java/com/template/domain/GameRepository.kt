package com.template.domain

import com.template.domain.model.QuestionAndAnswer

interface GameRepository {

    fun questionAndAnswer() : List<QuestionAndAnswer>

}