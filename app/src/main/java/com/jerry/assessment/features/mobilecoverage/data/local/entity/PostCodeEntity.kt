package com.jerry.assessment.features.mobilecoverage.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tbl_post_code")
data class PostCodeEntity(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "post_code_id")
    val id: Long = 0,

    @ColumnInfo(name = "post_code")
    val postCode: String
)