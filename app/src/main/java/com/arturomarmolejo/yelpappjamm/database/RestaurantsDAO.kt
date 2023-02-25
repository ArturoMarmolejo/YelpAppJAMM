package com.arturomarmolejo.yelpappjamm.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.arturomarmolejo.yelpappjamm.model.domain.DomainBusinesses
import com.arturomarmolejo.yelpappjamm.model.domain.DomainLocationHistory

@Dao
interface RestaurantsDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBusinesses(businesses: List<DomainBusinesses>?)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSearchedLocation(searchedLocationHistory: DomainLocationHistory)

    @Query("SELECT * from location_history")
    suspend fun getSearchedLocations(): List<DomainLocationHistory>

    @Query("SELECT * from location_history WHERE id LIKE :id")
    suspend fun getSearchLocationById(id: Int): DomainLocationHistory

    @Query("SELECT * FROM businesses_table")
    suspend fun getBusinessesById(businessesIdList: List<String?>?): List<DomainBusinesses>
}