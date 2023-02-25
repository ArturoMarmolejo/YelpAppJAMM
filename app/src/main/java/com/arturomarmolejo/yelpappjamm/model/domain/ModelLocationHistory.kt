package com.arturomarmolejo.yelpappjamm.model.domain

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types

class ModelLocationHistory (
    val id: Int?,
    val businessIdList: List<String>?,
    val searchLocationName: String?,
)

fun DomainLocationHistory?.mapToLocationHistory(): ModelLocationHistory {
    val moshi = Moshi.Builder().build()
    val stringListType = Types.newParameterizedType(List::class.java, String::class.java)
    val adapter: JsonAdapter<List<String>> = moshi.adapter(stringListType)

    return ModelLocationHistory(
        id = this?.id,
        businessIdList = adapter.fromJson(this?.businessIdList) ?: emptyList(),
        searchLocationName = this?.searchLocationName,

    )
}