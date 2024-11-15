package com.shuvo6904.gozayaan.data.data_source.remote

import com.shuvo6904.gozayaan.data.data_source.model.RecommendedItem
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("/")
    suspend fun getRecommendedLocation(): Response<List<RecommendedItem>>
}