package com.shuvo6904.gozayaan.data.data_source.local

import com.shuvo6904.gozayaan.R
import com.shuvo6904.gozayaan.data.model.PopularCategory

object PopularCategoriesDataSource {
    fun getPopularCategories(): List<PopularCategory> {
        return listOf(
            PopularCategory("Flights", R.drawable.ic_flight),
            PopularCategory("Hotels", R.drawable.ic_hotel),
            PopularCategory("Visa", R.drawable.ic_visa),
            PopularCategory("Buses", R.drawable.ic_buses)
        )
    }
}