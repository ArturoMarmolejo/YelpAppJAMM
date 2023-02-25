package com.arturomarmolejo.yelpappjamm.model.domain

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.arturomarmolejo.yelpappjamm.model.items.Businesses
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types

@Entity(tableName = "location_history")
data class DomainLocationHistory(
    @PrimaryKey(autoGenerate = true) val id: Int? = 0,
    val searchLocationName: String?  = "Search #: $id",
    val businessIdList: String
)
fun List<Businesses?>?.insertLocationHistory(): DomainLocationHistory {
    val moshi = Moshi.Builder().build()
    val adapter = moshi.adapter<List<String?>>(Types.newParameterizedType(List::class.java, String::class.java))

    return DomainLocationHistory(
        businessIdList = adapter.toJson(this?.map {  it?.id })
    )
}
