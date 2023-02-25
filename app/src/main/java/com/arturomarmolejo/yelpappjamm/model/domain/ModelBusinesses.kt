package com.arturomarmolejo.yelpappjamm.model.domain

import com.arturomarmolejo.yelpappjamm.model.items.Businesses
import com.arturomarmolejo.yelpappjamm.model.items.Location
import com.squareup.moshi.Moshi

data class ModelBusinesses (
    val id: String?,
    val distance: Double?,
    val imageUrl: String?,
    val isFavorite: Boolean,
    val location: Location?,
    val name: String?,
    val phone: String?,
    val price: String?,
    val rating: Double?,
    )

fun List<DomainBusinesses>?.mapToBusinesses(): List<ModelBusinesses>? {
    val moshi = Moshi.Builder().build()
    val locationAdapter = moshi.adapter<Location>(Location::class.java)
    return this!!.map { businesses ->
        ModelBusinesses(
            id = businesses?.id ?: "",
            distance = businesses?.distance ?: 0.0,
            location = businesses?.location?.let {
                locationAdapter.fromJson(
                    locationAdapter.toJson(
                        it
                    )
                )
            },
            name = businesses?.name ?: "",
            phone = businesses?.phone ?: "",
            price = businesses?.price ?: "",
            rating = businesses?.rating ?: 0.0,
            imageUrl = businesses?.imageUrl ?: "",
            isFavorite = businesses?.isFavorite ?: false
        )
    }
}