package com.template.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import androidx.appcompat.app.AppCompatActivity
import com.template.R
import com.template.databinding.ActivityMainBinding
import com.template.domain.model.Level
import com.template.domain.model.SaveSettings
import com.template.sharedpreferences.SharedPreferencesStorage
import com.template.utils.Constants.SETTINGS
import com.template.utils.Constants.UNKNOWN_MODEL

class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private var saveSettings: SaveSettings? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        SharedPreferencesStorage.with(application)

        setSpinner()
    }

    override fun onResume() {
        super.onResume()
        visibilityButton()
    }

    override fun onPause() {
        super.onPause()
        binding.buttonContinue.visibility = View.INVISIBLE
    }

    private fun setSpinner(){
        binding.spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                val level = resources.getStringArray(R.array.level)
                buttonClick (getLevel(level[p2]))
            }
            override fun onNothingSelected(p0: AdapterView<*>?) {
            }
        }
    }

    private fun buttonClick (level: Level) = with(binding){
        playButton.setOnClickListener {
            val intent = GameActivity.newIntentInstance(this@MainActivity, level)
            startActivity(intent)
        }

        buttonContinue.setOnClickListener {
            val levelContinue = Level.CONTINUE
            val intent = GameActivity.newIntentInstance(
                this@MainActivity, levelContinue
            )
            startActivity(intent)
        }
    }

    private fun visibilityButton(){
        saveSettings = SharedPreferencesStorage.getGameSettings<SaveSettings>(SETTINGS)
        Log.d("TAG", "visibilityButton $saveSettings")
        if (saveSettings != null){
            if (saveSettings!!.emojiListBinding.isNotEmpty()){
                binding.buttonContinue.visibility = View.VISIBLE
            }
        }
    }


    private fun getLevel(mode: String): Level = when(mode){
        "MODE_2X2" -> Level.MODE_2X2
        "MODE_4X4"-> Level.MODE_4X4
        "MODE_6X6" -> Level.MODE_6X6
        "MODE_8X8" -> Level.MODE_8X8
        else -> throw RuntimeException(UNKNOWN_MODEL)
    }
}