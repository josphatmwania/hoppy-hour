package com.josphatmwania.hoppyhour.feature_beer.data.datasource.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.josphatmwania.hoppyhour.common.Constants
import com.josphatmwania.hoppyhour.feature_beer.domain.model.Amount
import com.josphatmwania.hoppyhour.feature_beer.domain.model.BeerEntity

@Database(
    entities = [BeerEntity::class],
    version = Constants.DATABASE_VERSION,
    exportSchema = false
)
abstract class HoppyHourDatabase : RoomDatabase() {
    abstract fun dao(): HoppyHourDao

    /**
     * allows access to the methods to create or
     * get the database and uses the class name as the qualifier.
     */

    companion object{
        private val Instance: HoppyHourDatabase? = null
    }
}