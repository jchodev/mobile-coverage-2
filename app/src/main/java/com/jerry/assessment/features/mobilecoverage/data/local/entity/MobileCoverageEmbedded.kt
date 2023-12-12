package com.jerry.assessment.features.mobilecoverage.data.local.entity

import androidx.room.Embedded
import androidx.room.Relation

data class PostCodeWithAvailabilities(
    @Embedded val postCodeEntity: PostCodeEntity,
    @Relation(
        parentColumn = "post_code_id",
        entityColumn = "post_code_id"
    )
    val availabilities: List<AvailabilityEntity>
)
