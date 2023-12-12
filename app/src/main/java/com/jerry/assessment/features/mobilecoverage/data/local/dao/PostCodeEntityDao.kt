package com.jerry.assessment.features.mobilecoverage.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.jerry.assessment.features.mobilecoverage.data.local.entity.PostCodeEntity
import com.jerry.assessment.features.mobilecoverage.data.local.entity.PostCodeWithAvailabilities

@Dao
interface PostCodeEntityDao {

//    @Query("SELECT * FROM tbl_post_code")
//    suspend fun getAll(): List<PostCodeEntity>

    @Query("SELECT * FROM tbl_post_code WHERE post_code = :postCode")
    suspend fun getByPostCode(postCode: String): PostCodeEntity?

    @Insert
    suspend fun insert(entity: PostCodeEntity): Long

    @Update
    suspend fun update(entity: PostCodeEntity)

    @Delete
    suspend fun delete(entity: PostCodeEntity)

    @Transaction
    @Query("SELECT * FROM tbl_post_code WHERE post_code = :postCode")
    suspend fun getPostCodeWithAvailability(postCode: String): List<PostCodeWithAvailabilities>?
}