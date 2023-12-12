package com.jerry.assessment.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.jerry.assessment.features.mobilecoverage.data.local.dao.AvailabilityEntityDao
import com.jerry.assessment.features.mobilecoverage.data.local.dao.PostCodeEntityDao
import com.jerry.assessment.features.mobilecoverage.data.local.entity.AvailabilityEntity
import com.jerry.assessment.features.mobilecoverage.data.local.entity.PostCodeEntity

//https://waynestalk.com/android-room-relationships/
@Database(
    entities = [AvailabilityEntity::class, PostCodeEntity::class],
    version = 1
)
abstract class AssessmentDatabase: RoomDatabase() {
    abstract val availabilityEntityDao: AvailabilityEntityDao
    abstract val postCodeEntityDao: PostCodeEntityDao
}