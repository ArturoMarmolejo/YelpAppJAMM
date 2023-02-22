package com.arturomarmolejo.yelpappjamm.model.domain

import androidx.room.Entity
import com.arturomarmolejo.yelpappjamm.model.items.Businesses
import com.arturomarmolejo.yelpappjamm.model.items.Region
import com.arturomarmolejo.yelpappjamm.model.items.YelpResponse

@Entity(tableName = "businesses_table")
data class DomainBusinesses(
    val total: Int,
    val region: Region,
    val businesses: List<Businesses>,
)

fun YelpResponse.mapToBusinessesDomain(): DomainBusinesses =
    DomainBusinesses(
        total = this.total ?: 0,
        region = this.region ?: Region(),
        businesses = this.businesses ?: emptyList(),
    )