package com.josphatmwania.hoppyhour.feature_beer.presentation.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.josphatmwania.hoppyhour.common.UseCases
import com.josphatmwania.hoppyhour.feature_beer.domain.model.Beer
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListOfBeersViewModel @Inject constructor(
    private val useCases: UseCases
) : ViewModel() {

    private var _allBeers = MutableStateFlow<PagingData<Beer>?>(null)
    val allBeers = _allBeers.asStateFlow()

    init {
        viewModelScope.launch { fetchAllBeers() }
    }

    private suspend fun fetchAllBeers() {
        useCases.allBeers().onEach { beerPagingData ->
            _allBeers.value = beerPagingData
        }.cachedIn(viewModelScope)
            .launchIn(viewModelScope)
    }

}