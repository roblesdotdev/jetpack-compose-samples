package com.roblesdotdev.preferencesdemo.settings.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.roblesdotdev.preferencesdemo.preferences.data.PreferencesManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val preferencesManager: PreferencesManager
) : ViewModel() {
    private val _state = MutableStateFlow(SettingsState())
    val state = _state.asStateFlow()

    init {
        getInitialPreferences()
    }

    fun onEvent(event: SettingsEvent) {
        when (event) {
            is SettingsEvent.EnabledChanged -> updateEnabled(event.enabled)
            is SettingsEvent.NumberChanged -> updateNumber(event.number)
            SettingsEvent.SaveSettings -> save()
        }
    }

    private fun updateEnabled(enabled: Boolean) {
        _state.update { prevState -> prevState.copy(editEnabled = enabled) }
    }

    private fun updateNumber(number: Int?) {
        _state.update { prevState -> prevState.copy(numberOfItems = number) }
    }

    private fun save() {
        viewModelScope.launch {
            preferencesManager.setPreferredNumItems(state.value.numberOfItems)
            preferencesManager.setPreferredItemsEnabled(state.value.editEnabled)
        }
    }

    private fun getInitialPreferences() {
        viewModelScope.launch {
            val numItems = preferencesManager.getPreferredNumItems()
            val editEnabled = preferencesManager.getPreferredItemsEnabled()

            withContext(Dispatchers.Main) {
                _state.update { prevState ->
                    prevState.copy(
                        numberOfItems = numItems,
                        editEnabled = editEnabled,
                    )
                }
            }
        }
    }
}