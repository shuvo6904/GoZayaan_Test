package com.shuvo6904.gozayaan.domain.usecase.home

import javax.inject.Inject

data class HomeUseCase @Inject constructor(
    val getRecommendedLocationUseCase: GetRecommendedLocationUseCase,
    val getPopularCategoriesUseCase: GetPopularCategoriesUseCase
)
