package com.shuvo6904.gozayaan.domain.repository.home

import com.shuvo6904.gozayaan.data.UiState
import com.shuvo6904.gozayaan.data.model.RecommendedItem
import kotlinx.coroutines.flow.Flow

interface HomeRepository {
    suspend fun getRecommendedLocation(): Flow<UiState<List<RecommendedItem>>>
}