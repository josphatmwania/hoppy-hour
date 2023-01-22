package com.josphatmwania.hoppyhour.feature_onboarding.presentation.ui

import android.content.Context
import androidx.databinding.ObservableBoolean
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.josphatmwania.hoppyhour.common.SettingsDatastore
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnboardingActivityViewModel @Inject constructor(
    private val settingsDatastore: SettingsDatastore
) : ViewModel() {

    private var _showOnboarding = MutableStateFlow(true)
    val showOnboarding = _showOnboarding.asStateFlow()
    val showGetStartedButton = ObservableBoolean(false)

    init {
        setValues()
    }

    fun updateOnboadingState(showOnboarding: Boolean, context: Context) {
        viewModelScope.launch {
            settingsDatastore.updateOnboadingState(showOnboarding, context)
        }
    }

    private fun setValues() {
        viewModelScope.launch {
            settingsDatastore.preferenceFlow.collect {
                _showOnboarding.value = it
            }
            updateButtonVisibility()
        }
    }

    private suspend fun updateButtonVisibility() {
        delay(3000)
        showGetStartedButton.set(true)
    }

}