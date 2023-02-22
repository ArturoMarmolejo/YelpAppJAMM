package com.arturomarmolejo.yelpappjamm.database

import com.arturomarmolejo.yelpappjamm.model.domain.DomainBusinesses
import javax.inject.Inject

interface LocalRepository {
    suspend fun getBusinesses(): List<DomainBusinesses>
}

class LocalRepositoryImpl @Inject constructor(
 private val restaurantsDAO: RestaurantsDAO
): LocalRepository {
    override suspend fun getBusinesses(): List<DomainBusinesses> =
        restaurantsDAO.getBusinesses()
}

