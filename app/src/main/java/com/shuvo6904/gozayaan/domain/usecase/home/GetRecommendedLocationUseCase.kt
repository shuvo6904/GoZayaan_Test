package com.shuvo6904.gozayaan.domain.usecase.home

import com.shuvo6904.gozayaan.domain.repository.home.HomeRepository
import javax.inject.Inject

class GetRecommendedLocationUseCase @Inject constructor(
    private val homeRepository: HomeRepository
){
    suspend operator fun invoke() = homeRepository.getRecommendedLocation()
}