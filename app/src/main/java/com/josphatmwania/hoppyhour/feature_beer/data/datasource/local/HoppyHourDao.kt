package com.josphatmwania.hoppyhour.feature_beer.data.datasource.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.josphatmwania.hoppyhour.feature_beer.domain.model.Beer


@Dao
interface HoppyHourDao {



    @Insert
    suspend fun insert(beer: Beer)

}