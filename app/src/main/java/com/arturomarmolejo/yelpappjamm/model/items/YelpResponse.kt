package com.arturomarmolejo.yelpappjamm.model.items


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class YelpResponse(
    @Json(name = "businesses")
    val businesses: List<Businesses> = listOf(),
    @Json(name = "region")
    val region: Region = Region(),
    @Json(name = "total")
    val total: Int = 0
)