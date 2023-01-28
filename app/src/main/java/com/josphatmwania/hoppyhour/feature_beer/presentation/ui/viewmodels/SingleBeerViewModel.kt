package com.josphatmwania.hoppyhour.feature_beer.presentation.ui.viewmodels

import androidx.databinding.ObservableBoolean
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
class SingleBeerViewModel @Inject constructor(
    private val useCases: UseCases
) :ViewModel() {
    private var _beer = MutableStateFlow<BeerEvent?>(null)
    val beer = _beer.asStateFlow()

    suspend fun findBeer(id: Int) {
        useCases.findBeer(id).onEach { result ->
            when(result) {
                is Resource.Success -> {
                    _beer.value = BeerEvent(data = result.data)
                }
                is Resource.Error -> {
                    _beer.value = BeerEvent(message = result.message ?: "An unexpected error occurred")
                }
                is Resource.Loading -> {
                    _beer.value = BeerEvent(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}