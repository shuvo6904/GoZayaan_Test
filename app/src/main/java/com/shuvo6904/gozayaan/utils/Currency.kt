package com.shuvo6904.gozayaan.utils

enum class Currency(val symbol: String) {
    USD("$"),
    BDT("৳"),
    EUR("€"),
    GBP("£"),
    INR("₹"),
    JPY("¥"),
    AUD("A$"),
    CAD("CA$"),
    CNY("¥");

    companion object {
        fun fromString(currencyCode: String): Currency? {
            return entries.find { it.name.equals(currencyCode, ignoreCase = true) }
        }
    }
}
