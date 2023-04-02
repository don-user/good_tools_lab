package com.template.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Parcelable
import android.os.SystemClock
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.template.utils.Constants.ERROR_MSG
import com.template.utils.Constants.EXTRA_LEVEL
import com.template.utils.Constants.LONG_ZERO
import com.template.utils.Constants.QUESTION
import com.template.utils.Constants.SECONDS
import com.template.utils.Constants.SECONDS_3
import com.template.utils.Constants.SECONDS_HALF
import com.template.utils.Constants.formattedTimer
import com.template.R
import com.template.databinding.ActivityGameBinding
import com.template.domain.model.EmojiBinding
import com.template.domain.model.Level
import com.template.domain.model.SaveSettings
import com.template.utils.Mapper

class GameActivity : AppCompatActivity() {

    private val binding by lazy { ActivityGameBinding.inflate(layoutInflater) }

    private val mode2x2 by lazy {
        mutableListOf<EmojiBinding>().apply {
            add(EmojiBinding(null, binding.tvEmoji6, null))
            add(EmojiBinding(null, binding.tvEmoji7, null))
            add(EmojiBinding(null, binding.tvEmoji10, null))
            add(EmojiBinding(null, binding.tvEmoji11, null))
        }
    }

    private val mode4x4 by lazy {

        mutableListOf<EmojiBinding>().apply {
            add(EmojiBinding(null, binding.tvEmoji5, null))
            add(EmojiBinding(null, binding.tvEmoji6, null))
            add(EmojiBinding(null, binding.tvEmoji7, null))
            add(EmojiBinding(null, binding.tvEmoji8, null))
            add(EmojiBinding(null, binding.tvEmoji9, null))
            add(EmojiBinding(null, binding.tvEmoji10, null))
            add(EmojiBinding(null, binding.tvEmoji11, null))
            add(EmojiBinding(null, binding.tvEmoji12, null))
        }
    }

    private val mode6x6 by lazy {

        mutableListOf<EmojiBinding>().apply {
            add(EmojiBinding(null, binding.tvEmoji1, null))
            add(EmojiBinding(null, binding.tvEmoji2, null))
            add(EmojiBinding(null, binding.tvEmoji3, null))
            add(EmojiBinding(null, binding.tvEmoji4, null))
            add(EmojiBinding(null, binding.tvEmoji5, null))
            add(EmojiBinding(null, binding.tvEmoji6, null))
            add(EmojiBinding(null, binding.tvEmoji7, null))
            add(EmojiBinding(null, binding.tvEmoji8, null))
            add(EmojiBinding(null, binding.tvEmoji9, null))
            add(EmojiBinding(null, binding.tvEmoji10, null))
            add(EmojiBinding(null, binding.tvEmoji11, null))
            add(EmojiBinding(null, binding.tvEmoji12, null))

        }
    }

    private val mode8x8 by lazy {
        mutableListOf<EmojiBinding>().apply {
            add(EmojiBinding(null, binding.tvEmoji1, null))
            add(EmojiBinding(null, binding.tvEmoji2, null))
            add(EmojiBinding(null, binding.tvEmoji3, null))
            add(EmojiBinding(null, binding.tvEmoji4, null))
            add(EmojiBinding(null, binding.tvEmoji5, null))
            add(EmojiBinding(null, binding.tvEmoji6, null))
            add(EmojiBinding(null, binding.tvEmoji7, null))
            add(EmojiBinding(null, binding.tvEmoji8, null))
            add(EmojiBinding(null, binding.tvEmoji9, null))
            add(EmojiBinding(null, binding.tvEmoji10, null))
            add(EmojiBinding(null, binding.tvEmoji11, null))
            add(EmojiBinding(null, binding.tvEmoji12, null))
            add(EmojiBinding(null, binding.tvEmoji13, null))
            add(EmojiBinding(null, binding.tvEmoji14, null))
            add(EmojiBinding(null, binding.tvEmoji15, null))
            add(EmojiBinding(null, binding.tvEmoji16, null))
        }
    }

    private val modeContinue by lazy {
        mutableListOf<EmojiBinding>().apply {
            add(EmojiBinding(null, binding.tvEmoji1, null))
            add(EmojiBinding(null, binding.tvEmoji2, null))
            add(EmojiBinding(null, binding.tvEmoji3, null))
            add(EmojiBinding(null, binding.tvEmoji4, null))
            add(EmojiBinding(null, binding.tvEmoji5, null))
            add(EmojiBinding(null, binding.tvEmoji6, null))
            add(EmojiBinding(null, binding.tvEmoji7, null))
            add(EmojiBinding(null, binding.tvEmoji8, null))
            add(EmojiBinding(null, binding.tvEmoji9, null))
            add(EmojiBinding(null, binding.tvEmoji10, null))
            add(EmojiBinding(null, binding.tvEmoji11, null))
            add(EmojiBinding(null, binding.tvEmoji12, null))
            add(EmojiBinding(null, binding.tvEmoji13, null))
            add(EmojiBinding(null, binding.tvEmoji14, null))
            add(EmojiBinding(null, binding.tvEmoji15, null))
            add(EmojiBinding(null, binding.tvEmoji16, null))
        }
    }

    private var countDownTimer: CountDownTimer? = null
    private var time = LONG_ZERO
    private lateinit var viewModelGame: ViewModelGame
    private var gameLevel: Level? = null
    private lateinit var settingsSave: SaveSettings

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        gameLevel = intent.getParcelableExtra<Level>(EXTRA_LEVEL)
        val currentMode = when(gameLevel){
            Level.MODE_2X2 -> mode2x2
            Level.MODE_4X4 -> mode4x4
            Level.MODE_6X6 -> mode6x6
            Level.MODE_8X8 -> mode8x8
            Level.CONTINUE -> modeContinue
            else -> throw RuntimeException(ERROR_MSG)
        }

        if (gameLevel != Level.CONTINUE) startCountDownTimer(SECONDS_3, false)

        viewModelGame = ViewModelProvider(
            this, ViewModelGameFactory(application, currentMode, binding)
        )[ViewModelGame::class.java]

        viewModelGame.getGameSettings(gameLevel)

        observeViewModel()
    }

    override fun onPause() {
        super.onPause()
        countDownTimer?.cancel()
        countDownTimer = null

        stopTimer()
        settingPause()
    }

    override fun onResume() {
        super.onResume()
        if(time != LONG_ZERO) settingStart(time)
    }

    private fun observeViewModel() = with(viewModelGame){

        listGameSetting.observe(this@GameActivity){
            setupEmoji(it)
            setOnClickEmoji(it)
            settingsSave = SaveSettings(
                Mapper(binding).mapEmojiBindingToGameSettings(it),
                gameLevel!!
            )
        }

        refreshItem.observe(this@GameActivity){if (it) startCountDownTimer(SECONDS_HALF, it)}

        visibility.observe(this@GameActivity){ hideTextView(it) }

        finishGame.observe(this@GameActivity){
            if (it) {
                    viewModelGame.cleanSharedPreferences()
                    stopTimer()
                    launchFragment()
            }
        }

        resettingTimeAndLvl.observe(this@GameActivity){

            settingStart(it.currentTime)
            gameLevel = it.modeLvl
        }
    }

    private fun setupEmoji(modeList: MutableList<EmojiBinding>){

        for (i in modeList) {
            i.textViewBinding?.visibility = View.VISIBLE
            if(i.status) {
                i.textViewBinding?.setText(i.emoji!!)
            }else {
                i.textViewBinding?.text = QUESTION
            }
        }
    }

    private fun setOnClickEmoji(modeList: MutableList<EmojiBinding>) {
        for (item in modeList) {
            item.textViewBinding?.setOnClickListener {
                if (!item.status) {
                    viewModelGame.clickItem(item)
                }
            }
        }
    }

    private fun hideTextView(itemList: List<EmojiBinding>){
        startCountDownTimer(SECONDS_HALF, true, itemList)
    }

    private fun settingPause() = with(viewModelGame){
        setQuestionIcons()
        settingsSave.currentTime = time
        saveSharedPreferences(settingsSave)
    }

    private fun settingStart(lastTime: Long){
       viewModelGame.setIcons()
       startCountDownTimer(SECONDS_3, false, lastTime = lastTime)
    }

    private fun startCountDownTimer(
        time: Long,
        flag: Boolean,
        itemList: List<EmojiBinding>? = null,
        lastTime: Long = 0L
    ){

        countDownTimer = object : CountDownTimer(time, SECONDS) {
            override fun onTick(p0: Long) {
                if (!flag) {
                    binding.viewTimer.text = formattedTimer(p0)
                }
            }
            override fun onFinish()  {
                if(itemList != null) {
                    for (i in itemList) i.textViewBinding?.visibility = View.INVISIBLE

                }else{
                    viewModelGame.setQuestionIcons()
                    if (!flag) {
                        startTimer(getRestOfTime(lastTime))
                    }
                }
            }
        }
        countDownTimer?.start()
}

    private fun startTimer(baseTime: Long){
        binding.viewTimer.apply {
            base = baseTime
            start()
        }
    }

    private fun stopTimer(){
        binding.viewTimer.apply {
            time = getRestOfTime(binding.viewTimer.base)
            stop()
        }
    }

    private fun getRestOfTime(time: Long): Long{
        return SystemClock.elapsedRealtime() - time
    }

    private fun launchFragment(){
        supportFragmentManager.popBackStack()
        supportFragmentManager.beginTransaction()
            .replace(R.id.mainContainer, FinishFragment.newInstance(time, gameLevel!!))
            .commit()
    }

    companion object{

        fun newIntentInstance(context: Context, level: Level) : Intent{
            val intent = Intent(context, GameActivity::class.java)
                intent.putParcelableExtra(EXTRA_LEVEL, level)
            return intent
        }
        private fun Intent.putParcelableExtra(key: String, value: Parcelable) {
            putExtra(key, value)
        }
    }
}