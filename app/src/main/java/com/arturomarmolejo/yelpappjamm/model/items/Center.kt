package com.arturomarmolejo.yelpappjamm.model.items


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Center(
    @Json(name = "latitude")
    val latitude: Double = 0.0,
    @Json(name = "longitude")
    val longitude: Double = 0.0
)