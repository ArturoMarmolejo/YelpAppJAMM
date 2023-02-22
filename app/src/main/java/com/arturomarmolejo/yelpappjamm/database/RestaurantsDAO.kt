package com.arturomarmolejo.yelpappjamm.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.arturomarmolejo.yelpappjamm.model.domain.DomainBusinesses

@Dao
interface RestaurantsDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBusinesses(businesses: DomainBusinesses)

    @Query("SELECT * FROM businesses_table")
    suspend fun getBusinesses(): List<DomainBusinesses>
}