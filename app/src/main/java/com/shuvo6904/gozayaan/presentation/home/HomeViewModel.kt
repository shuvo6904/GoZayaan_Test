package com.shuvo6904.gozayaan.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shuvo6904.gozayaan.data.UiState
import com.shuvo6904.gozayaan.data.model.PopularCategory
import com.shuvo6904.gozayaan.data.model.RecommendedItem
import com.shuvo6904.gozayaan.domain.usecase.home.HomeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val homeUseCase: HomeUseCase
) : ViewModel() {
    private val _uiStateRecommendedLocation = MutableStateFlow<UiState<List<RecommendedItem>>>(UiState.Loading)
    val uiStateRecommendedLocation: StateFlow<UiState<List<RecommendedItem>>> = _uiStateRecommendedLocation

    private val _uiStatePopularCategories = MutableStateFlow<UiState<List<PopularCategory>>>(UiState.Loading)
    val uiStatePopularCategories: StateFlow<UiState<List<PopularCategory>>> = _uiStatePopularCategories

    fun getRecommendedLocation() = viewModelScope.launch(Dispatchers.IO) {
        val resultFlow = homeUseCase.getRecommendedLocationUseCase()
        resultFlow.collect{ result ->
            _uiStateRecommendedLocation.value = result
        }
    }

    fun getPopularCategories() = viewModelScope.launch(Dispatchers.IO) {
        val resultFlow = homeUseCase.getPopularCategoriesUseCase()
        resultFlow.collect{ result ->
            _uiStatePopularCategories.value = result
        }
    }
}