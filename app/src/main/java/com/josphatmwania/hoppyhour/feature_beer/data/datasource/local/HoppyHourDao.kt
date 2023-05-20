package com.josphatmwania.hoppyhour.feature_beer.data.datasource.local

import androidx.room.*
import com.josphatmwania.hoppyhour.feature_beer.domain.model.Beer
import kotlinx.coroutines.flow.Flow

@Dao
interface HoppyHourDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(beer: Beer)

    @Update(onConflict = OnConflictStrategy.IGNORE)
    suspend fun update(beer: Beer)

    @Delete
    suspend fun delete(beer: Beer)

    @Query("SELECT * from beer WHERE id = :id")
    fun getBeer(id: Int) : Flow<Beer>



}