package com.josphatmwania.hoppyhour.feature_beer.presentation.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.josphatmwania.hoppyhour.common.Resource
import com.josphatmwania.hoppyhour.common.UseCases
import com.josphatmwania.hoppyhour.feature_beer.presentation.events.BeerEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class BeerItemViewModel @Inject constructor(
    private val useCases: UseCases
) : ViewModel() {



}