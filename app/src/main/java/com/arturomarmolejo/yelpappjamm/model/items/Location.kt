package com.arturomarmolejo.yelpappjamm.model.items


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Location(
    @Json(name = "address1")
    val address1: String = "",
    @Json(name = "address2")
    val address2: String? = null,
    @Json(name = "address3")
    val address3: String? = null,
    @Json(name = "city")
    val city: String = "",
    @Json(name = "country")
    val country: String = "",
    @Json(name = "display_address")
    val displayAddress: List<String> = listOf(),
    @Json(name = "state")
    val state: String = "",
    @Json(name = "zip_code")
    val zipCode: String = ""
)