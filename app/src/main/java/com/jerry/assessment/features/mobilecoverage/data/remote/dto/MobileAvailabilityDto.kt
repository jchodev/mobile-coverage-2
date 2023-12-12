package com.jerry.assessment.features.mobilecoverage.data.remote.dto

import com.squareup.moshi.Json

data class MobileAvailabilityDto(

    /*
    Refer: https://api.ofcom.org.uk/api-details#api=ofcom-connected-nations-api&operation=CoverageByPostCodeGet&definition=MobileAvailability
    0 = Clear (no signal predicted)
    1 = Red (reliable signal unlikely)
    2 = Amber (may experience problems with connectivity)
    3 = Green (likely to have good coverage and receive a data rate to support basic web services)
    4 = Enhanced (likely to have good coverage indoors and to receive an enhanced data rate to support multimedia services)
     */
    @field:Json(name = "UPRN") val uprn: Long?,
    @field:Json(name = "AddressShortDescription") val addressShortDescription: String?,
    @field:Json(name = "PostCode") val postCode: String?,

    //EE
    @field:Json(name = "EEVoiceOutdoor") val eeVoiceOutdoor: Int?,
    @field:Json(name = "EEVoiceOutdoorNo4g") val eeVoiceOutdoorNo4g: Int?,
    @field:Json(name = "EEVoiceIndoor") val eeVoiceIndoor: Int?,
    @field:Json(name = "EEVoiceIndoorNo4g") val eeVoiceIndoorNo4g: Int?,
    @field:Json(name = "EEDataOutdoor") val eeDataOutdoor: Int?,
    @field:Json(name = "EEDataOutdoorNo4g") val eeDataOutdoorNo4g: Int?,
    @field:Json(name = "EEDataIndoor") val eeDataIndoor: Int?,
    @field:Json(name = "EEDataIndoorNo4g") val eeDataIndoorNo4g: Int?,

    //Three
    @field:Json(name = "H3VoiceOutdoor") val h3VoiceOutdoor: Int?,
    @field:Json(name = "H3VoiceOutdoorNo4g") val h3VoiceOutdoorNo4g: Int?,
    @field:Json(name = "H3VoiceIndoor") val h3VoiceIndoor: Int?,
    @field:Json(name = "H3VoiceIndoorNo4g") val h3VoiceIndoorNo4g: Int?,
    @field:Json(name = "H3DataOutdoor") val h3DataOutdoor: Int?,
    @field:Json(name = "H3DataOutdoorNo4g") val h3DataOutdoorNo4g: Int?,
    @field:Json(name = "H3DataIndoor") val h3DataIndoor: Int?,
    @field:Json(name = "H3DataIndoorNo4g") val h3DataIndoorNo4g: Int?,

    //O2
    @field:Json(name = "TFVoiceOutdoor") val o2VoiceOutdoor: Int?,
    @field:Json(name = "TFVoiceOutdoorNo4g") val o2VoiceOutdoorNo4g: Int?,
    @field:Json(name = "TFVoiceIndoor") val o2VoiceIndoor: Int?,
    @field:Json(name = "TFVoiceIndoorNo4g") val o2VoiceIndoorNo4g: Int?,
    @field:Json(name = "TFDataOutdoor") val o2DataOutdoor: Int?,
    @field:Json(name = "TFDataOutdoorNo4g") val o2DataOutdoorNo4g: Int?,
    @field:Json(name = "TFDataIndoor") val o2DataIndoor: Int?,
    @field:Json(name = "TFDataIndoorNo4g") val o2DataIndoorNo4g: Int?,

    //Vodafone
    @field:Json(name = "VOVoiceOutdoor") val voVoiceOutdoor: Int?,
    @field:Json(name = "VOVoiceOutdoorNo4g") val voVoiceOutdoorNo4g: Int?,
    @field:Json(name = "VOVoiceIndoor") val voVoiceIndoor: Int?,
    @field:Json(name = "VOVoiceIndoorNo4g") val voVoiceIndoorNo4g: Int?,
    @field:Json(name = "VODataOutdoor") val voDataOutdoor: Int?,
    @field:Json(name = "VODataOutdoorNo4g") val voDataOutdoorNo4g: Int?,
    @field:Json(name = "VODataIndoor") val voDataIndoor: Int?,
    @field:Json(name = "VODataIndoorNo4g") val voDataIndoorNo4g: Int?
)