package com.arturomarmolejo.yelpappjamm.model.domain

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.arturomarmolejo.yelpappjamm.model.items.Businesses
import com.arturomarmolejo.yelpappjamm.model.items.Location
import com.google.gson.Gson
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types


@Entity(tableName = "businesses_table")
data class DomainBusinesses(
    @PrimaryKey val id: String,
    val distance: Double,
    val isFavorite: Boolean = false,
    val location: Location?,
    val name: String?,
    val phone: String?,
    val price: String,
    val rating: Double?,
    val imageUrl: String?,
)

fun List<Businesses?>?.mapToBusinessesDomain(): List<DomainBusinesses>? {
    val moshi = Moshi.Builder().build()
    val locationAdapter = moshi.adapter<Location>(Location::class.java)
    return this!!.map { businesses ->
        DomainBusinesses(
            id = businesses?.id ?: "",
            distance = businesses?.distance ?: 0.0,
            location = businesses?.location?.let { locationAdapter.fromJson(locationAdapter.toJson(it)) },
            name = businesses?.name ?: "",
            phone = businesses?.phone ?: "",
            price = businesses?.price ?: "" ,
            rating = businesses?.rating ?: 0.0,
            imageUrl = businesses?.imageUrl ?: ""
        )
    }
}





