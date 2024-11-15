package com.shuvo6904.gozayaan.data.repository.home

import com.shuvo6904.gozayaan.data.UiState
import com.shuvo6904.gozayaan.data.data_source.local.PopularCategoriesDataSource
import com.shuvo6904.gozayaan.data.data_source.remote.ApiService
import com.shuvo6904.gozayaan.data.model.PopularCategory
import com.shuvo6904.gozayaan.data.model.RecommendedItem
import com.shuvo6904.gozayaan.domain.repository.home.HomeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import java.net.UnknownHostException
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val popularCategoriesDataSources: PopularCategoriesDataSource
) : HomeRepository {
    override suspend fun getRecommendedLocation(): Flow<UiState<List<RecommendedItem>>> = flow {
        emit(UiState.Loading)
        try {
            val response = apiService.getRecommendedLocation()
            val data = response.body()
            if (response.isSuccessful && data != null) {
                emit(UiState.Success(data))
            } else {
                emit(UiState.Error("Something Went Wrong"))
            }
        } catch (e: UnknownHostException) {
            emit(UiState.Error("No Internet Connection"))
        } catch (e: IOException) {
            emit(UiState.Error("Server Not Responding"))
        } catch (e: Exception) {
            emit(UiState.Error(e.message.toString()))
        }
    }

    override suspend fun getPopularCategories(): Flow<UiState<List<PopularCategory>>> = flow {
        emit(UiState.Loading)
        try {
            val response = popularCategoriesDataSources.getPopularCategories()
            if (response.isNotEmpty()) {
                emit(UiState.Success(response))
            } else {
                emit(UiState.Error("No Popular Categories Found"))
            }
        } catch (e: Exception) {
            emit(UiState.Error(e.message.toString()))
        }
    }
}