package com.josphatmwania.hoppyhour.feature_beer.data.datasource.local

import androidx.room.*
import com.josphatmwania.hoppyhour.feature_beer.domain.model.Beer


@Dao
interface HoppyHourDao {



    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(beer: Beer)

    @Update(onConflict = OnConflictStrategy.IGNORE)
    suspend fun update(beer: Beer)


}