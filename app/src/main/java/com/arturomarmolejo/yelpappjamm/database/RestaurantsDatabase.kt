package com.arturomarmolejo.yelpappjamm.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.arturomarmolejo.yelpappjamm.model.domain.DomainBusinesses

@Database(
    entities = [
        DomainBusinesses::class
    ],
    version = 1
)

abstract class RestaurantsDatabase: RoomDatabase() {
    abstract fun getRestaurantsDAO(): RestaurantsDAO
}