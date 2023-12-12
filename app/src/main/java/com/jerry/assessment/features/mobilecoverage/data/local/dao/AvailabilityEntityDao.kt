package com.jerry.assessment.features.mobilecoverage.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.jerry.assessment.features.mobilecoverage.data.local.entity.AvailabilityEntity
import com.jerry.assessment.features.mobilecoverage.data.local.entity.PostCodeEntity

@Dao
interface AvailabilityEntityDao {

//    @Query("SELECT * FROM tbl_availability")
//    suspend fun getAll(): List<PostCodeEntity>

    @Query("SELECT * FROM tbl_availability WHERE availability_id = :availabilityId")
    suspend fun getById(availabilityId: Long): AvailabilityEntity?


    @Query("SELECT * FROM tbl_availability WHERE post_code_id = :postCodeId")
    suspend fun getByPostCodeId(postCodeId: Long): List<AvailabilityEntity>?

    @Insert
    suspend fun insert(entity: AvailabilityEntity): Long

    @Update
    suspend fun update(entity: AvailabilityEntity)

    @Delete
    suspend fun delete(entity: AvailabilityEntity)


}