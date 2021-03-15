package com.android.hogwartsenciclopedia.viewmodel

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.android.hogwartsenciclopedia.repository.CharactersRepository
import com.android.hogwartsenciclopedia.utils.PreferenceManager
import com.android.hogwartsenciclopedia.utils.SharedPreferenceLiveData
import kotlinx.coroutines.launch

class AllCharactersViewModel @ViewModelInject constructor(
    private val repository: CharactersRepository,
    @Assisted private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val preferenceManager = PreferenceManager.getInstance(null)
    private val sharedPreferences = preferenceManager.sharedPreferences

    private val _eventNavigateToCharacter = MutableLiveData<String>()
    val eventNavigateToCharacter: LiveData<String>
        get() = _eventNavigateToCharacter

    val characters = repository.getAll()

    fun getAllCharacters() {
        viewModelScope.launch {
            repository.refresh()
        }
    }

    fun onCharacterClicked(characterId: String) {
        viewModelScope.launch {
            _eventNavigateToCharacter.value = characterId
        }
    }

    fun onEventNavigateCompleted() {
        _eventNavigateToCharacter.value = null
    }

    fun onChangeTheme() {
        when(preferenceManager.currentTheme) {
            SharedPreferenceLiveData.HUFFLEPUFF -> editPreference(SharedPreferenceLiveData.GRYFFINDOR)
            SharedPreferenceLiveData.GRYFFINDOR -> editPreference(SharedPreferenceLiveData.SLYTHERIN)
            SharedPreferenceLiveData.SLYTHERIN -> editPreference(SharedPreferenceLiveData.RAVENCLAW)
            SharedPreferenceLiveData.RAVENCLAW -> editPreference(SharedPreferenceLiveData.HUFFLEPUFF)
        }
    }

    private fun editPreference(value: String) {
        preferenceManager.editPreferences(value)
    }
}