package com.template.ui

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.template.data.GameRepositoryImpl
import com.template.databinding.ActivityGameBinding
import com.template.domain.model.QuestionAndAnswer
import com.template.domain.usecase.QuestionAnswerUseCase
import com.template.utils.Constants
import com.template.utils.Constants.EMPTY
import com.template.utils.Constants.EXTRA
import com.template.utils.Constants.EXTRA_RIGHT
import com.template.utils.Constants.EXTRA_WRONG
import com.template.utils.Constants.RIGHT
import com.template.utils.Constants.WRONG
import com.template.utils.Constants.ZERO
import com.template.utils.formattedTimer
import com.template.utils.makeToast
import com.template.utils.numberOfQuestions

class GameActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityGameBinding.inflate(layoutInflater)
    }

    private val generateQuestion = QuestionAnswerUseCase(GameRepositoryImpl)

    private var level = EMPTY

    private var timer: CountDownTimer? = null

    private var item: QuestionAndAnswer? = null

    var countRightAnswer = ZERO
    var countWrongAnswer = ZERO
    private var cnt = ZERO

    private val tvAnswer by lazy {

        mutableListOf<TextView>().apply {
            add(binding.tvAnswer1)
            add(binding.tvAnswer2)
            add(binding.tvAnswer3)
            add(binding.tvAnswer4)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        level = intent.getStringExtra(EXTRA) ?: EMPTY

        item = generateQuestion.getQuestionAndAnswer()

        nextQuestion()
        setOnClickAnswer()
    }

    override fun onStart() {
        super.onStart()

        startTimer()
    }

    private fun setOnClickAnswer() {
        for (item in tvAnswer) {
            item.setOnClickListener { chooseAnswer(item.text.toString()) }
        }
    }

    private fun nextQuestion() = with(binding) {
        item = generateQuestion.getQuestionAndAnswer()
        tvQuestion.text = item?.question

        val listOption = item?.optionAnswer?.shuffled() ?: emptyList()

        for (i in listOption.indices) {

            tvAnswer[i].text = listOption[i]
        }
    }

    private fun chooseAnswer(answer: String) {

        val countQuestion = numberOfQuestions(level)

        if (answer == item?.rightAnswer) {
            makeToast(this, RIGHT)
            countRightAnswer++
        } else {

            makeToast(this, String.format(WRONG, item?.rightAnswer))
            countWrongAnswer++
        }
        cnt++

        if (countQuestion != cnt) {

            nextQuestion()
        } else {
            timer?.cancel()
            intentForResult(countWrongAnswer).apply {
                startActivity(this)
            }
            finish()
        }
    }

    private fun startTimer() {
        timer = object : CountDownTimer(Constants.TIMER_SEC, Constants.SECONDS) {
            override fun onTick(p0: Long) {
                binding.tvTimer.text = formattedTimer(p0)
            }
            override fun onFinish()  {
                countWrongAnswer = numberOfQuestions(level) - countRightAnswer

                intentForResult(countWrongAnswer).apply {
                    startActivity(this)
                }
                finish()
            }

        }
        timer?.start()
    }

    private fun intentForResult(countWrongAnswer: Int): Intent {
        return Intent(this, ResultActivity::class.java).apply {
            putExtra(EXTRA_RIGHT, countRightAnswer)
            putExtra(EXTRA_WRONG, countWrongAnswer)
            putExtra(EXTRA, level)
        }
    }
}