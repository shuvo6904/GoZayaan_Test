package com.shuvo6904.gozayaan.data.data_source.model

import com.google.gson.annotations.SerializedName

data class RecommendedItem(
    @SerializedName("property_name")
    val propertyName: String? = null,
    @SerializedName("location")
    val location: String? = null,
    @SerializedName("rating")
    val rating: Double? = null,
    @SerializedName("description")
    val description: String? = null,
    @SerializedName("fare")
    val fare: Double? = null,
    @SerializedName("fare_unit")
    val fareUnit: String? = null,
    @SerializedName("is_available")
    val isAvailable: Boolean? = null,
    @SerializedName("hero_image")
    val heroImage: String? = null,
    @SerializedName("detail_image")
    val detailImage: String? = null,
    @SerializedName("currency")
    val currency: String? = null
)
