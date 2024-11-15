package com.shuvo6904.gozayaan.di

import com.shuvo6904.gozayaan.data.data_source.local.PopularCategoriesDataSource
import com.shuvo6904.gozayaan.data.data_source.remote.ApiService
import com.shuvo6904.gozayaan.data.repository.home.HomeRepositoryImpl
import com.shuvo6904.gozayaan.domain.repository.home.HomeRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    @Singleton
    fun provideHomeRepository(apiService: ApiService, popularCategoriesDataSources: PopularCategoriesDataSource): HomeRepository {
        return HomeRepositoryImpl(apiService, popularCategoriesDataSources)
    }
}