package com.arturomarmolejo.yelpappjamm.model.items


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Businesses(
    @Json(name = "alias")
    val alias: String = "",
    @Json(name = "categories")
    val categories: List<Category> = listOf(),
    @Json(name = "coordinates")
    val coordinates: Coordinates = Coordinates(),
    @Json(name = "display_phone")
    val displayPhone: String = "",
    @Json(name = "distance")
    val distance: Double = 0.0,
    @Json(name = "id")
    val id: String = "",
    @Json(name = "image_url")
    val imageUrl: String = "",
    @Json(name = "is_closed")
    val isClosed: Boolean = false,
    @Json(name = "location")
    val location: Location = Location(),
    @Json(name = "name")
    val name: String = "",
    @Json(name = "phone")
    val phone: String = "",
    @Json(name = "price")
    val price: String = "",
    @Json(name = "rating")
    val rating: Double = 0.0,
    @Json(name = "review_count")
    val reviewCount: Int = 0,
    @Json(name = "transactions")
    val transactions: List<String> = listOf(),
    @Json(name = "url")
    val url: String = ""
)