package com.template.ui

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.template.databinding.ActivityGameBinding
import com.template.domain.GetGameSetting
import com.template.domain.model.EmojiBinding
import com.template.domain.model.Level
import com.template.domain.model.SaveSettings
import com.template.sharedpreferences.SharedPreferencesStorage
import com.template.utils.Constants.SETTINGS
import com.template.utils.Mapper

class ViewModelGame(
    application: Application, emojiBinding: List<EmojiBinding>, binding: ActivityGameBinding
): AndroidViewModel(application) {

    private val getGameSettings = GetGameSetting(application)

    private var emojiMutableList = emojiBinding.toMutableList()
    private val _listGameSetting = MutableLiveData<MutableList<EmojiBinding>>()
    val listGameSetting: LiveData<MutableList<EmojiBinding>>
    get() = _listGameSetting

    private val _refreshItem = MutableLiveData<Boolean>()
    val refreshItem: LiveData<Boolean>
        get() = _refreshItem

    private val _visibility = MutableLiveData<List<EmojiBinding>>()
    val visibility: LiveData<List<EmojiBinding>>
        get() = _visibility

    private val _finishGame = MutableLiveData<Boolean>()
    val finishGame: LiveData<Boolean>
        get() = _finishGame

    private val _resettingTimeAndLvl = MutableLiveData<SaveSettings>()
    val resettingTimeAndLvl: LiveData<SaveSettings>
        get() = _resettingTimeAndLvl

    private val mapper = Mapper(binding)
    private var idR: EmojiBinding? = null

    fun getGameSettings(level: Level?){

        if (level == Level.CONTINUE){
            getSharedPreferences()
        } else{
            val gameSettingsList = getGameSettings.getGameList(level?.numberCard)
            gameSettingsList.shuffle()

            for (i in gameSettingsList.indices){
                emojiMutableList[i].id = gameSettingsList[i].id
                emojiMutableList[i].emoji = gameSettingsList[i].emoji
                emojiMutableList[i].status = gameSettingsList[i].status
            }
        }
        _listGameSetting.value = emojiMutableList
    }

    fun clickItem(item: EmojiBinding){

        changeStatus(item)

        if (idR != null && idR?.id != item.id ){
            if (item.emoji == idR!!.emoji) {
                emojiMutableList.removeAll{it.id == idR?.id}
                emojiMutableList.removeAll{it.id == item.id}
                _listGameSetting.value = emojiMutableList
                _visibility.value = listOf(idR!!, item)
                idR = null
            } else {
                idR = null
                _refreshItem.value = true
            }
        }else idR = item

        if (emojiMutableList.size == 0){
            _finishGame.value = true
        }
    }
     private fun changeStatus(item: EmojiBinding){
         if (!item.status) {
             val index = emojiMutableList.indexOf(item)
             val newItem = item.copy(status = true)
             emojiMutableList[index] = newItem
             _listGameSetting.value = emojiMutableList
         }
     }

    fun setQuestionIcons(){
        emojiMutableList.map {
            it.status = false
        }
        _listGameSetting.value = emojiMutableList
        _refreshItem.value = false
        idR = null
    }

    fun setIcons(){
        emojiMutableList.map {
            it.status = true
        }
        _listGameSetting.value = emojiMutableList
    }

    fun saveSharedPreferences(saveSettings: SaveSettings){
        SharedPreferencesStorage.saveGameSettings(saveSettings, SETTINGS)
    }
    private fun getSharedPreferences(){
        val saveSettings = SharedPreferencesStorage.getGameSettings<SaveSettings>(SETTINGS)
        emojiMutableList = saveSettings?.emojiListBinding?.let {
            mapper.mapGameSettingsToEmojiBindings( it )
        } as MutableList<EmojiBinding>
        _resettingTimeAndLvl.value = saveSettings

    }

    fun cleanSharedPreferences(){
        SharedPreferencesStorage.sharedPref.edit().clear().apply()
    }
}