package com.arturomarmolejo.yelpappjamm.service

import com.arturomarmolejo.yelpappjamm.model.domain.DomainBusinesses
import com.arturomarmolejo.yelpappjamm.model.items.YelpResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

//Client ID
//gMe6k8D_8b_rXlqIMRzk_A
//
//API Key
//dLqWGBHu0noiq7_OX006gu0BDTVD4Mvbdwf2qYq_KDIVsYBxKMKnKQ4VcRthi6_2BH4Ttupf56KHLsgB-AC9k88pQ4K-9uUko9OYYSxw_finzW7_-w8E6fsuGsjyY3Yx
//https://api.yelp.com/v3/businesses/search?latitude=33.87336&longitude=-84.469482&term=restaurants&attributes=hot_and_new&sort_by=best_match&limit=20


interface YelpApi {
    @GET(SEARCH)
    suspend fun getHotAndNewRestaurants(
        @Query("latitude") latitude: String?,
        @Query("longitude") longitude: String?,
        @Query("term") term: String = "restaurants",
        @Query("attributes") attributes: String = "hot_and_new",
        @Query("sort_by") sort_by: String = "best_match",
        @Query("limit") limit: Int = 20,
    ): Response<YelpResponse>

    companion object {
        const val BASE_URL = "https://api.yelp.com/v3/businesses/"
        const val SEARCH = "search"

    }

}