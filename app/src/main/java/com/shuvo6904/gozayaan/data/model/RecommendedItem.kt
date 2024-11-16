package com.shuvo6904.gozayaan.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
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
    @SerializedName("detail_images")
    val detailImages: List<String?>? = null,
    @SerializedName("currency")
    val currency: String? = null
): Parcelable
