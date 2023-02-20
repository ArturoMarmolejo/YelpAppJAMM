package com.arturomarmolejo.yelpappjamm.model.items


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Category(
    @Json(name = "alias")
    val alias: String = "",
    @Json(name = "title")
    val title: String = ""
)