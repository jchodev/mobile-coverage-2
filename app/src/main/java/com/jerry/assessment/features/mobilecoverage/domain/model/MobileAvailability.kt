package com.jerry.assessment.features.mobilecoverage.domain.model


data class MobileAvailability (
    val addressShortDescription: String,
    val postCode: String = "",

    //EE
    val eeVoiceOutdoor: Int = 0,
    val eeVoiceOutdoorNo4g: Int = 0,
    val eeVoiceIndoor: Int = 0,
    val eeVoiceIndoorNo4g: Int = 0,
    val eeDataOutdoor: Int = 0,
    val eeDataOutdoorNo4g: Int = 0,
    val eeDataIndoor: Int = 0,
    val eeDataIndoorNo4g: Int = 0,

    //Three
    val h3VoiceOutdoor: Int = 0,
    val h3VoiceOutdoorNo4g: Int = 0,
    val h3VoiceIndoor: Int = 0,
    val h3VoiceIndoorNo4g: Int = 0,
    val h3DataOutdoor: Int = 0,
    val h3DataOutdoorNo4g: Int = 0,
    val h3DataIndoor: Int = 0,
    val h3DataIndoorNo4g: Int = 0,

    //O2
    val o2VoiceOutdoor: Int = 0,
    val o2VoiceOutdoorNo4g: Int = 0,
    val o2VoiceIndoor: Int = 0,
    val o2VoiceIndoorNo4g: Int = 0,
    val o2DataOutdoor: Int = 0,
    val o2DataOutdoorNo4g: Int = 0,
    val o2DataIndoor: Int = 0,
    val o2DataIndoorNo4g: Int = 0,

    //Vodafone
    val voVoiceOutdoor: Int = 0,
    val voVoiceOutdoorNo4g: Int = 0,
    val voVoiceIndoor: Int = 0,
    val voVoiceIndoorNo4g: Int = 0,
    val voDataOutdoor: Int = 0,
    val voDataOutdoorNo4g: Int = 0,
    val voDataIndoor: Int = 0,
    val voDataIndoorNo4g: Int = 0,

    val signalTypeList : List<ProviderSignalType>
)

enum class SignalType(val value: String) {
    DATA_INDOOR("data_indoor"),
    DATA_OUTDOOR("data_outdoor"),
    VOICE_INDOOR("voice_indoor"),
    VOICE_OUTDOOR("voice_outdoor")
}

enum class Provider(val value: String) {
    EE("ee"),
    THREE("three"),
    O2("o2"),
    VODAFONE("vodafone")
}

data class ProviderSignalType(
    val signalType : SignalType,
    val providerSignalList: List<ProviderSignal>
)

data class ProviderSignal(
    val provider: Provider,
    val fourG: Int,
    val nonFourG: Int
)
