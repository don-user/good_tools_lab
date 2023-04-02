package com.template.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.template.R
import com.template.databinding.ActivityResultBinding
import com.template.utils.Constants.EXTRA
import com.template.utils.Constants.EXTRA_RIGHT
import com.template.utils.Constants.EXTRA_WRONG

class ResultActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityResultBinding.inflate(layoutInflater)
    }
    private var level: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val correctAnswer = intent.getIntExtra(EXTRA_RIGHT, 0)
        val incorrectAnswer = intent.getIntExtra(EXTRA_WRONG, 0)
        level = intent.getStringExtra(EXTRA)

        valueAssignment(correctAnswer, incorrectAnswer)
        clickButton()

    }

    private fun valueAssignment(correctAnswer: Int, incorrectAnswer: Int) = with(binding){
        if (correctAnswer > incorrectAnswer) {
            imageResult.setImageResource(R.drawable.emoji_emotions_yellowp)
        }else imageResult.setImageResource(R.drawable.emoticon_frown_yellow)

        tvMaxRight.text = String.format(
            getString(R.string.your_correct_answer_s),
            correctAnswer.toString()
        )
        tvMaxWrong.text = String.format(
            getString(R.string.your_incorrect_answers_s),
            incorrectAnswer.toString()
        )
    }

    private fun clickButton() = with(binding){
        again.setOnClickListener {
            Intent(this@ResultActivity, GameActivity::class.java).apply {

                putExtra(EXTRA, level)
                startActivity(this)
            }
            finish()
        }
        backMenu.setOnClickListener {
            finish()
        }
    }
}