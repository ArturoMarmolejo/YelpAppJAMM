package com.arturomarmolejo.yelpappjamm.service

import com.arturomarmolejo.yelpappjamm.model.domain.DomainBusinesses
import com.arturomarmolejo.yelpappjamm.model.items.YelpResponse
import retrofit2.Response
import javax.inject.Inject

interface YelpRepository {
    suspend fun retrieveBusinesses(
        latitude: String,
        longitude: String
    ): Response<YelpResponse>
}

class YelpRepositoryImpl @Inject constructor(
    private val yelpApi: YelpApi
) : YelpRepository {
    override suspend fun retrieveBusinesses(
        latitude: String,
        longitude: String
    ): Response<YelpResponse> {
        return yelpApi.getHotAndNewRestaurants(latitude, longitude)
    }
}