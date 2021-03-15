package com.android.hogwartsenciclopedia.viewmodel

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.android.hogwartsenciclopedia.domain.FullCharacterModel
import com.android.hogwartsenciclopedia.repository.CharactersRepository
import com.android.hogwartsenciclopedia.utils.PreferenceManager
import com.android.hogwartsenciclopedia.utils.SharedPreferenceLiveData
import kotlinx.coroutines.launch

class CharacterViewModel @ViewModelInject constructor(
    private val repository: CharactersRepository,
    @Assisted savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val preferenceManager = PreferenceManager.getInstance(null)
    private val sharedPreferences = preferenceManager.sharedPreferences

    private val characterId = savedStateHandle.get<String>("character_name")!!
    private val _character = MutableLiveData<FullCharacterModel>()
    val character: LiveData<FullCharacterModel> get() = _character

    companion object {
        const val TAG = "CharacterViewModel"
    }

    init {
        getCharacter()
    }

    private fun getCharacter(){
        viewModelScope.launch {
            _character.value = repository.getById(characterId) as FullCharacterModel
        }
    }

    fun onUpdateTheme(house: String?) {
        if (house != null) {
            if (shouldChangeTheme(house)) {
                preferenceManager.editPreferences(house)
            }
        }
    }

    private fun shouldChangeTheme(house: String): Boolean {
        return preferenceManager.currentTheme != house
    }

    private fun editPreference(value: String) {
        sharedPreferences.edit().putString(SharedPreferenceLiveData.CURRENT_THEME, value).apply()
    }
}