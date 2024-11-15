package com.shuvo6904.gozayaan.di

import com.shuvo6904.gozayaan.data.data_source.local.PopularCategoriesDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {
    @Provides
    @Singleton
    fun providePopularCategoriesDataSource(): PopularCategoriesDataSource = PopularCategoriesDataSource
}