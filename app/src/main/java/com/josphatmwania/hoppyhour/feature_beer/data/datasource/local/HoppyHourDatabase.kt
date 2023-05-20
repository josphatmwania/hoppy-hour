package com.josphatmwania.hoppyhour.feature_beer.data.datasource.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.josphatmwania.hoppyhour.common.Constants
import com.josphatmwania.hoppyhour.feature_beer.domain.model.Amount
import com.josphatmwania.hoppyhour.feature_beer.domain.model.BeerEntity
import okhttp3.internal.concurrent.TaskRunner.Companion.INSTANCE

@Database(
    entities = [BeerEntity::class],
    version = Constants.DATABASE_VERSION,
    exportSchema = false
)
abstract class HoppyHourDatabase : RoomDatabase() {
    abstract fun dao(): HoppyHourDao

    /**
     * This Object allows access to the methods to create or
     * get the database and uses the class name as the qualifier.
     * variable keeps a reference to the database, when one has been created.
     * This helps maintain a single instance of the database opened at a given time
     */


    companion object {
        @Volatile
        private var Instance: HoppyHourDatabase? = null

        fun getDatabase(context: Context): HoppyHourDatabase {
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, HoppyHourDatabase::class.java, "Hoppy_Hour_database")
                    .build()
                    .also { Instance = it }
            }

        }
    }






}