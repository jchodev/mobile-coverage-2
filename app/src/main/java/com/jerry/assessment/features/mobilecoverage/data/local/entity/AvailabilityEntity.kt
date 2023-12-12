package com.jerry.assessment.features.mobilecoverage.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey


@Entity(
    tableName = "tbl_availability"
)

data class AvailabilityEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "availability_id")
    val availabilityId: Long = 0,

    val uprn: Long?,
    @ColumnInfo(name = "address_short_description") val addressShortDescription: String?,
    @ColumnInfo(name = "post_code_id") val postCodeId: Long,

    //EE TODO rename the column name
    @ColumnInfo(name = "EEVoiceOutdoor") val eeVoiceOutdoor: Int?,
    @ColumnInfo(name = "EEVoiceOutdoorNo4g") val eeVoiceOutdoorNo4g: Int?,
    @ColumnInfo(name = "EEVoiceIndoor") val eeVoiceIndoor: Int?,
    @ColumnInfo(name = "EEVoiceIndoorNo4g") val eeVoiceIndoorNo4g: Int?,
    @ColumnInfo(name = "EEDataOutdoor") val eeDataOutdoor: Int?,
    @ColumnInfo(name = "EEDataOutdoorNo4g") val eeDataOutdoorNo4g: Int?,
    @ColumnInfo(name = "EEDataIndoor") val eeDataIndoor: Int?,
    @ColumnInfo(name = "EEDataIndoorNo4g") val eeDataIndoorNo4g: Int?,

    //Three TODO rename the column name
    @ColumnInfo(name = "H3VoiceOutdoor") val h3VoiceOutdoor: Int?,
    @ColumnInfo(name = "H3VoiceOutdoorNo4g") val h3VoiceOutdoorNo4g: Int?,
    @ColumnInfo(name = "H3VoiceIndoor") val h3VoiceIndoor: Int?,
    @ColumnInfo(name = "H3VoiceIndoorNo4g") val h3VoiceIndoorNo4g: Int?,
    @ColumnInfo(name = "H3DataOutdoor") val h3DataOutdoor: Int?,
    @ColumnInfo(name = "H3DataOutdoorNo4g") val h3DataOutdoorNo4g: Int?,
    @ColumnInfo(name = "H3DataIndoor") val h3DataIndoor: Int?,
    @ColumnInfo(name = "H3DataIndoorNo4g") val h3DataIndoorNo4g: Int?,

    //O2 TODO rename the column name
    @ColumnInfo(name = "TFVoiceOutdoor") val o2VoiceOutdoor: Int?,
    @ColumnInfo(name = "TFVoiceOutdoorNo4g") val o2VoiceOutdoorNo4g: Int?,
    @ColumnInfo(name = "TFVoiceIndoor") val o2VoiceIndoor: Int?,
    @ColumnInfo(name = "TFVoiceIndoorNo4g") val o2VoiceIndoorNo4g: Int?,
    @ColumnInfo(name = "TFDataOutdoor") val o2DataOutdoor: Int?,
    @ColumnInfo(name = "TFDataOutdoorNo4g") val o2DataOutdoorNo4g: Int?,
    @ColumnInfo(name = "TFDataIndoor") val o2DataIndoor: Int?,
    @ColumnInfo(name = "TFDataIndoorNo4g") val o2DataIndoorNo4g: Int?,

    //Vodafone TODO rename the column name
    @ColumnInfo(name = "VOVoiceOutdoor") val voVoiceOutdoor: Int?,
    @ColumnInfo(name = "VOVoiceOutdoorNo4g") val voVoiceOutdoorNo4g: Int?,
    @ColumnInfo(name = "VOVoiceIndoor") val voVoiceIndoor: Int?,
    @ColumnInfo(name = "VOVoiceIndoorNo4g") val voVoiceIndoorNo4g: Int?,
    @ColumnInfo(name = "VODataOutdoor") val voDataOutdoor: Int?,
    @ColumnInfo(name = "VODataOutdoorNo4g") val voDataOutdoorNo4g: Int?,
    @ColumnInfo(name = "VODataIndoor") val voDataIndoor: Int?,
    @ColumnInfo(name = "VODataIndoorNo4g") val voDataIndoorNo4g: Int?
)