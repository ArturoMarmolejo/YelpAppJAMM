package com.arturomarmolejo.yelpappjamm.database

import com.arturomarmolejo.yelpappjamm.model.domain.DomainBusinesses
import com.arturomarmolejo.yelpappjamm.model.domain.DomainLocationHistory
import javax.inject.Inject

interface LocalRepository {

    suspend fun getBusinessesById(businessesIdList: List<String?>?): List<DomainBusinesses>
    suspend fun getSearchedLocations(): List<DomainLocationHistory>?
    suspend fun getSearchedLocationById(id: Int): DomainLocationHistory
    suspend fun insertBusinesses(businesses: List<DomainBusinesses>?)
    suspend fun insertSearchedLocation(searchedLocationHistory: DomainLocationHistory): Unit

}

class LocalRepositoryImpl @Inject constructor(
 private val restaurantsDAO: RestaurantsDAO
): LocalRepository {

    override suspend fun getBusinessesById(businessesIdList: List<String?>?): List<DomainBusinesses> {
        return restaurantsDAO.getBusinessesById(businessesIdList)
    }

    override suspend fun getSearchedLocations(): List<DomainLocationHistory>? {
        return restaurantsDAO.getSearchedLocations()
    }

    override suspend fun getSearchedLocationById(id: Int): DomainLocationHistory {
        return restaurantsDAO.getSearchLocationById(id)
    }


    override suspend fun insertBusinesses(businesses: List<DomainBusinesses>?) =
        restaurantsDAO.insertBusinesses(businesses)


    override suspend fun insertSearchedLocation(searchedLocationHistory: DomainLocationHistory): Unit =
        restaurantsDAO.insertSearchedLocation(searchedLocationHistory)

}

