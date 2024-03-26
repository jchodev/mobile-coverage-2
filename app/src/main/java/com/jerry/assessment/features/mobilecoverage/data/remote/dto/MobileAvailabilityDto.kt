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
    @Json(name = "UPRN") val uprn: Long?,
    @Json(name = "AddressShortDescription") val addressShortDescription: String?,
    @Json(name = "PostCode") val postCode: String?,

    //EE
    @Json(name = "EEVoiceOutdoor") val eeVoiceOutdoor: Int?,
    @Json(name = "EEVoiceOutdoorNo4g") val eeVoiceOutdoorNo4g: Int?,
    @Json(name = "EEVoiceIndoor") val eeVoiceIndoor: Int?,
    @Json(name = "EEVoiceIndoorNo4g") val eeVoiceIndoorNo4g: Int?,
    @Json(name = "EEDataOutdoor") val eeDataOutdoor: Int?,
    @Json(name = "EEDataOutdoorNo4g") val eeDataOutdoorNo4g: Int?,
    @Json(name = "EEDataIndoor") val eeDataIndoor: Int?,
    @Json(name = "EEDataIndoorNo4g") val eeDataIndoorNo4g: Int?,

    //Three
    @Json(name = "H3VoiceOutdoor") val h3VoiceOutdoor: Int?,
    @Json(name = "H3VoiceOutdoorNo4g") val h3VoiceOutdoorNo4g: Int?,
    @Json(name = "H3VoiceIndoor") val h3VoiceIndoor: Int?,
    @Json(name = "H3VoiceIndoorNo4g") val h3VoiceIndoorNo4g: Int?,
    @Json(name = "H3DataOutdoor") val h3DataOutdoor: Int?,
    @Json(name = "H3DataOutdoorNo4g") val h3DataOutdoorNo4g: Int?,
    @Json(name = "H3DataIndoor") val h3DataIndoor: Int?,
    @Json(name = "H3DataIndoorNo4g") val h3DataIndoorNo4g: Int?,

    //O2
    @Json(name = "TFVoiceOutdoor") val o2VoiceOutdoor: Int?,
    @Json(name = "TFVoiceOutdoorNo4g") val o2VoiceOutdoorNo4g: Int?,
    @Json(name = "TFVoiceIndoor") val o2VoiceIndoor: Int?,
    @Json(name = "TFVoiceIndoorNo4g") val o2VoiceIndoorNo4g: Int?,
    @Json(name = "TFDataOutdoor") val o2DataOutdoor: Int?,
    @Json(name = "TFDataOutdoorNo4g") val o2DataOutdoorNo4g: Int?,
    @Json(name = "TFDataIndoor") val o2DataIndoor: Int?,
    @Json(name = "TFDataIndoorNo4g") val o2DataIndoorNo4g: Int?,

    //Vodafone
    @Json(name = "VOVoiceOutdoor") val voVoiceOutdoor: Int?,
    @Json(name = "VOVoiceOutdoorNo4g") val voVoiceOutdoorNo4g: Int?,
    @Json(name = "VOVoiceIndoor") val voVoiceIndoor: Int?,
    @Json(name = "VOVoiceIndoorNo4g") val voVoiceIndoorNo4g: Int?,
    @Json(name = "VODataOutdoor") val voDataOutdoor: Int?,
    @Json(name = "VODataOutdoorNo4g") val voDataOutdoorNo4g: Int?,
    @Json(name = "VODataIndoor") val voDataIndoor: Int?,
    @Json(name = "VODataIndoorNo4g") val voDataIndoorNo4g: Int?
)