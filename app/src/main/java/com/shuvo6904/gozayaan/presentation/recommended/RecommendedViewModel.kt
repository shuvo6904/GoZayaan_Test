package com.shuvo6904.gozayaan.presentation.recommended

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shuvo6904.gozayaan.data.UiState
import com.shuvo6904.gozayaan.data.model.RecommendedItem
import com.shuvo6904.gozayaan.domain.usecase.home.GetRecommendedLocationUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecommendedViewModel @Inject constructor(
    private val recommendedLocationUseCase: GetRecommendedLocationUseCase
): ViewModel() {
    private val _uiStateRecommendedLocation = MutableStateFlow<UiState<List<RecommendedItem>>>(UiState.Loading)
    val uiStateRecommendedLocation: StateFlow<UiState<List<RecommendedItem>>> = _uiStateRecommendedLocation

    fun getRecommendedLocation() = viewModelScope.launch(Dispatchers.IO) {
        val resultFlow = recommendedLocationUseCase()
        resultFlow.collect{ result ->
            _uiStateRecommendedLocation.value = result
        }
    }
}