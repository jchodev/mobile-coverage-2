package com.jerry.assessment.features.mobilecoverage.data.mapper


import com.jerry.assessment.features.mobilecoverage.data.local.entity.AvailabilityEntity
import com.jerry.assessment.features.mobilecoverage.data.remote.dto.MobileAvailabilityDto
import com.jerry.assessment.features.mobilecoverage.domain.model.MobileAvailability
import com.jerry.assessment.features.mobilecoverage.domain.model.Provider
import com.jerry.assessment.features.mobilecoverage.domain.model.ProviderSignal
import com.jerry.assessment.features.mobilecoverage.domain.model.ProviderSignalType
import com.jerry.assessment.features.mobilecoverage.domain.model.SignalType


fun MobileAvailabilityDto.toAvailabilityEntity(postCodeId: Long):  AvailabilityEntity{
    //ee
    val eeVoiceOutdoor = eeVoiceOutdoor ?: 0
    val eeVoiceOutdoorNo4g = eeVoiceOutdoorNo4g ?: 0
    val eeVoiceIndoor = eeVoiceIndoor ?: 0
    val eeVoiceIndoorNo4g = eeVoiceIndoorNo4g ?: 0
    val eeDataOutdoor = eeDataOutdoor ?: 0
    val eeDataOutdoorNo4g = eeDataOutdoorNo4g ?: 0
    val eeDataIndoor = eeDataIndoor ?: 0
    val eeDataIndoorNo4g = eeDataIndoorNo4g ?: 0

    //h3
    val h3VoiceOutdoor = h3VoiceOutdoor ?: 0
    val h3VoiceOutdoorNo4g = h3VoiceOutdoorNo4g ?: 0
    val h3VoiceIndoor = h3VoiceIndoor ?: 0
    val h3VoiceIndoorNo4g = h3VoiceIndoorNo4g ?: 0
    val h3DataOutdoor = h3DataOutdoor ?: 0
    val h3DataOutdoorNo4g = h3DataOutdoorNo4g ?: 0
    val h3DataIndoor = h3DataIndoor ?: 0
    val h3DataIndoorNo4g = h3DataIndoorNo4g ?: 0

    //o2
    val o2VoiceOutdoor = o2VoiceOutdoor ?: 0
    val o2VoiceOutdoorNo4g = o2VoiceOutdoorNo4g ?: 0
    val o2VoiceIndoor = o2VoiceIndoor ?: 0
    val o2VoiceIndoorNo4g = o2VoiceIndoorNo4g ?: 0
    val o2DataOutdoor = o2DataOutdoor ?: 0
    val o2DataOutdoorNo4g = o2DataOutdoorNo4g ?: 0
    val o2DataIndoor = o2DataIndoor ?: 0
    val o2DataIndoorNo4g = o2DataIndoorNo4g ?: 0

    //vo
    val voVoiceOutdoor = voVoiceOutdoor ?: 0
    val voVoiceOutdoorNo4g = voVoiceOutdoorNo4g ?: 0
    val voVoiceIndoor = voVoiceIndoor ?: 0
    val voVoiceIndoorNo4g = voVoiceIndoorNo4g ?: 0
    val voDataOutdoor = voDataOutdoor ?: 0
    val voDataOutdoorNo4g = voDataOutdoorNo4g ?: 0
    val voDataIndoor = voDataIndoor ?: 0
    val voDataIndoorNo4g = voDataIndoorNo4g ?: 0

    return AvailabilityEntity(
        uprn = uprn,
        postCodeId = postCodeId,
        addressShortDescription = addressShortDescription ?: "",
        eeVoiceOutdoor = eeVoiceOutdoor,
        eeVoiceOutdoorNo4g = eeVoiceOutdoorNo4g,
        eeVoiceIndoor = eeVoiceIndoor,
        eeVoiceIndoorNo4g = eeVoiceIndoorNo4g,
        eeDataOutdoor = eeDataOutdoor,
        eeDataOutdoorNo4g = eeDataOutdoorNo4g,
        eeDataIndoor = eeDataIndoor,
        eeDataIndoorNo4g = eeDataIndoorNo4g,

        h3VoiceOutdoor = h3VoiceOutdoor,
        h3VoiceOutdoorNo4g = h3VoiceOutdoorNo4g,
        h3VoiceIndoor = h3VoiceIndoor,
        h3VoiceIndoorNo4g = h3VoiceIndoorNo4g,
        h3DataOutdoor = h3DataOutdoor,
        h3DataOutdoorNo4g = h3DataOutdoorNo4g,
        h3DataIndoor = h3DataIndoor,
        h3DataIndoorNo4g = h3DataIndoorNo4g,

        o2VoiceOutdoor = o2VoiceOutdoor,
        o2VoiceOutdoorNo4g = o2VoiceOutdoorNo4g,
        o2VoiceIndoor = o2VoiceIndoor,
        o2VoiceIndoorNo4g = o2VoiceIndoorNo4g,
        o2DataOutdoor = o2DataOutdoor,
        o2DataOutdoorNo4g = o2DataOutdoorNo4g,
        o2DataIndoor = o2DataIndoor,
        o2DataIndoorNo4g = o2DataIndoorNo4g,

        voVoiceOutdoor = voVoiceOutdoor,
        voVoiceOutdoorNo4g = voVoiceOutdoorNo4g,
        voVoiceIndoor = voVoiceIndoor,
        voVoiceIndoorNo4g = voVoiceIndoorNo4g,
        voDataOutdoor = voDataOutdoor,
        voDataOutdoorNo4g = voDataOutdoorNo4g,
        voDataIndoor = voDataIndoor,
        voDataIndoorNo4g = voDataIndoorNo4g
    )
}

fun AvailabilityEntity.toMobileAvailability(postCode: String): MobileAvailability {
    //ee
    val eeVoiceOutdoor = eeVoiceOutdoor ?: 0
    val eeVoiceOutdoorNo4g = eeVoiceOutdoorNo4g ?: 0
    val eeVoiceIndoor = eeVoiceIndoor ?: 0
    val eeVoiceIndoorNo4g = eeVoiceIndoorNo4g ?: 0
    val eeDataOutdoor = eeDataOutdoor ?: 0
    val eeDataOutdoorNo4g = eeDataOutdoorNo4g ?: 0
    val eeDataIndoor = eeDataIndoor ?: 0
    val eeDataIndoorNo4g = eeDataIndoorNo4g ?: 0

    //h3
    val h3VoiceOutdoor = h3VoiceOutdoor ?: 0
    val h3VoiceOutdoorNo4g = h3VoiceOutdoorNo4g ?: 0
    val h3VoiceIndoor = h3VoiceIndoor ?: 0
    val h3VoiceIndoorNo4g = h3VoiceIndoorNo4g ?: 0
    val h3DataOutdoor = h3DataOutdoor ?: 0
    val h3DataOutdoorNo4g = h3DataOutdoorNo4g ?: 0
    val h3DataIndoor = h3DataIndoor ?: 0
    val h3DataIndoorNo4g = h3DataIndoorNo4g ?: 0

    //o2
    val o2VoiceOutdoor = o2VoiceOutdoor ?: 0
    val o2VoiceOutdoorNo4g = o2VoiceOutdoorNo4g ?: 0
    val o2VoiceIndoor = o2VoiceIndoor ?: 0
    val o2VoiceIndoorNo4g = o2VoiceIndoorNo4g ?: 0
    val o2DataOutdoor = o2DataOutdoor ?: 0
    val o2DataOutdoorNo4g = o2DataOutdoorNo4g ?: 0
    val o2DataIndoor = o2DataIndoor ?: 0
    val o2DataIndoorNo4g = o2DataIndoorNo4g ?: 0

    //vo
    val voVoiceOutdoor = voVoiceOutdoor ?: 0
    val voVoiceOutdoorNo4g = voVoiceOutdoorNo4g ?: 0
    val voVoiceIndoor = voVoiceIndoor ?: 0
    val voVoiceIndoorNo4g = voVoiceIndoorNo4g ?: 0
    val voDataOutdoor = voDataOutdoor ?: 0
    val voDataOutdoorNo4g = voDataOutdoorNo4g ?: 0
    val voDataIndoor = voDataIndoor ?: 0
    val voDataIndoorNo4g = voDataIndoorNo4g ?: 0

    return MobileAvailability(
        addressShortDescription = addressShortDescription ?: "",
        postCode = postCode ?: "",

        eeVoiceOutdoor = eeVoiceOutdoor,
        eeVoiceOutdoorNo4g = eeVoiceOutdoorNo4g,
        eeVoiceIndoor = eeVoiceIndoor,
        eeVoiceIndoorNo4g = eeVoiceIndoorNo4g,
        eeDataOutdoor = eeDataOutdoor,
        eeDataOutdoorNo4g = eeDataOutdoorNo4g,
        eeDataIndoor = eeDataIndoor,
        eeDataIndoorNo4g = eeDataIndoorNo4g,

        h3VoiceOutdoor = h3VoiceOutdoor,
        h3VoiceOutdoorNo4g = h3VoiceOutdoorNo4g,
        h3VoiceIndoor = h3VoiceIndoor,
        h3VoiceIndoorNo4g = h3VoiceIndoorNo4g,
        h3DataOutdoor = h3DataOutdoor,
        h3DataOutdoorNo4g = h3DataOutdoorNo4g,
        h3DataIndoor = h3DataIndoor,
        h3DataIndoorNo4g = h3DataIndoorNo4g,

        o2VoiceOutdoor = o2VoiceOutdoor,
        o2VoiceOutdoorNo4g = o2VoiceOutdoorNo4g,
        o2VoiceIndoor = o2VoiceIndoor,
        o2VoiceIndoorNo4g = o2VoiceIndoorNo4g,
        o2DataOutdoor = o2DataOutdoor,
        o2DataOutdoorNo4g = o2DataOutdoorNo4g,
        o2DataIndoor = o2DataIndoor,
        o2DataIndoorNo4g = o2DataIndoorNo4g,

        voVoiceOutdoor = voVoiceOutdoor,
        voVoiceOutdoorNo4g = voVoiceOutdoorNo4g,
        voVoiceIndoor = voVoiceIndoor,
        voVoiceIndoorNo4g = voVoiceIndoorNo4g,
        voDataOutdoor = voDataOutdoor,
        voDataOutdoorNo4g = voDataOutdoorNo4g,
        voDataIndoor = voDataIndoor,
        voDataIndoorNo4g = voDataIndoorNo4g,

        signalTypeList = listOf(
            //Indoor Data
            ProviderSignalType(
                signalType = SignalType.DATA_INDOOR,
                providerSignalList = listOf(
                    //ee
                    ProviderSignal(
                        provider = Provider.EE,
                        fourG = eeDataIndoor,
                        nonFourG = eeDataIndoorNo4g
                    ),
                    //three
                    ProviderSignal(
                        provider = Provider.THREE,
                        fourG = h3DataIndoor,
                        nonFourG = h3DataIndoorNo4g
                    ),
                    //o2
                    ProviderSignal(
                        provider = Provider.O2,
                        fourG = o2DataIndoor,
                        nonFourG = o2DataIndoorNo4g
                    ),
                    //vo
                    ProviderSignal(
                        provider = Provider.VODAFONE,
                        fourG = voDataIndoor,
                        nonFourG = voDataIndoorNo4g
                    )
                )
            ),

            //OutDoor Data
            ProviderSignalType(
                signalType = SignalType.DATA_OUTDOOR,
                providerSignalList = listOf(
                    //ee
                    ProviderSignal(
                        provider = Provider.EE,
                        fourG = eeDataOutdoor,
                        nonFourG = eeDataOutdoorNo4g
                    ),
                    //three
                    ProviderSignal(
                        provider = Provider.THREE,
                        fourG = h3DataOutdoor,
                        nonFourG = h3DataOutdoorNo4g
                    ),
                    //o2
                    ProviderSignal(
                        provider = Provider.O2,
                        fourG = o2DataOutdoor,
                        nonFourG = o2DataOutdoorNo4g
                    ),
                    //vo
                    ProviderSignal(
                        provider = Provider.VODAFONE,
                        fourG = voDataOutdoor,
                        nonFourG = voDataOutdoorNo4g
                    )
                )
            ),

            //Indoor Voice
            ProviderSignalType(
                signalType = SignalType.VOICE_INDOOR,
                providerSignalList = listOf(
                    //ee
                    ProviderSignal(
                        provider = Provider.EE,
                        fourG = eeVoiceIndoor,
                        nonFourG = eeVoiceIndoorNo4g
                    ),
                    //three
                    ProviderSignal(
                        provider = Provider.THREE,
                        fourG = h3VoiceIndoor,
                        nonFourG = h3VoiceIndoorNo4g
                    ),
                    //o2
                    ProviderSignal(
                        provider = Provider.O2,
                        fourG = o2VoiceIndoor,
                        nonFourG = o2VoiceIndoorNo4g
                    ),
                    //vo
                    ProviderSignal(
                        provider = Provider.VODAFONE,
                        fourG = voVoiceIndoor,
                        nonFourG = voVoiceIndoorNo4g
                    )
                )
            ),

            //OutDoor Voice
            ProviderSignalType(
                signalType = SignalType.VOICE_OUTDOOR,
                providerSignalList = listOf(
                    //ee
                    ProviderSignal(
                        provider = Provider.EE,
                        fourG = eeVoiceOutdoor,
                        nonFourG = eeVoiceOutdoorNo4g
                    ),
                    //three
                    ProviderSignal(
                        provider = Provider.THREE,
                        fourG = h3VoiceOutdoor,
                        nonFourG = h3VoiceOutdoorNo4g
                    ),
                    //o2
                    ProviderSignal(
                        provider = Provider.O2,
                        fourG = o2VoiceOutdoor,
                        nonFourG = o2VoiceOutdoorNo4g
                    ),
                    //vo
                    ProviderSignal(
                        provider = Provider.VODAFONE,
                        fourG = voVoiceOutdoor,
                        nonFourG = voVoiceOutdoorNo4g
                    )
                )
            )
        )
    )
}

fun MobileAvailabilityDto.toMobileAvailability(): MobileAvailability {

    //ee
    val eeVoiceOutdoor = eeVoiceOutdoor ?: 0
    val eeVoiceOutdoorNo4g = eeVoiceOutdoorNo4g ?: 0
    val eeVoiceIndoor = eeVoiceIndoor ?: 0
    val eeVoiceIndoorNo4g = eeVoiceIndoorNo4g ?: 0
    val eeDataOutdoor = eeDataOutdoor ?: 0
    val eeDataOutdoorNo4g = eeDataOutdoorNo4g ?: 0
    val eeDataIndoor = eeDataIndoor ?: 0
    val eeDataIndoorNo4g = eeDataIndoorNo4g ?: 0

    //h3
    val h3VoiceOutdoor = h3VoiceOutdoor ?: 0
    val h3VoiceOutdoorNo4g = h3VoiceOutdoorNo4g ?: 0
    val h3VoiceIndoor = h3VoiceIndoor ?: 0
    val h3VoiceIndoorNo4g = h3VoiceIndoorNo4g ?: 0
    val h3DataOutdoor = h3DataOutdoor ?: 0
    val h3DataOutdoorNo4g = h3DataOutdoorNo4g ?: 0
    val h3DataIndoor = h3DataIndoor ?: 0
    val h3DataIndoorNo4g = h3DataIndoorNo4g ?: 0

    //o2
    val o2VoiceOutdoor = o2VoiceOutdoor ?: 0
    val o2VoiceOutdoorNo4g = o2VoiceOutdoorNo4g ?: 0
    val o2VoiceIndoor = o2VoiceIndoor ?: 0
    val o2VoiceIndoorNo4g = o2VoiceIndoorNo4g ?: 0
    val o2DataOutdoor = o2DataOutdoor ?: 0
    val o2DataOutdoorNo4g = o2DataOutdoorNo4g ?: 0
    val o2DataIndoor = o2DataIndoor ?: 0
    val o2DataIndoorNo4g = o2DataIndoorNo4g ?: 0

    //vo
    val voVoiceOutdoor = voVoiceOutdoor ?: 0
    val voVoiceOutdoorNo4g = voVoiceOutdoorNo4g ?: 0
    val voVoiceIndoor = voVoiceIndoor ?: 0
    val voVoiceIndoorNo4g = voVoiceIndoorNo4g ?: 0
    val voDataOutdoor = voDataOutdoor ?: 0
    val voDataOutdoorNo4g = voDataOutdoorNo4g ?: 0
    val voDataIndoor = voDataIndoor ?: 0
    val voDataIndoorNo4g = voDataIndoorNo4g ?: 0

    return MobileAvailability(
        addressShortDescription = addressShortDescription ?: "",
        postCode = postCode ?: "",

        eeVoiceOutdoor = eeVoiceOutdoor,
        eeVoiceOutdoorNo4g = eeVoiceOutdoorNo4g,
        eeVoiceIndoor = eeVoiceIndoor,
        eeVoiceIndoorNo4g = eeVoiceIndoorNo4g,
        eeDataOutdoor = eeDataOutdoor,
        eeDataOutdoorNo4g = eeDataOutdoorNo4g,
        eeDataIndoor = eeDataIndoor,
        eeDataIndoorNo4g = eeDataIndoorNo4g,

        h3VoiceOutdoor = h3VoiceOutdoor,
        h3VoiceOutdoorNo4g = h3VoiceOutdoorNo4g,
        h3VoiceIndoor = h3VoiceIndoor,
        h3VoiceIndoorNo4g = h3VoiceIndoorNo4g,
        h3DataOutdoor = h3DataOutdoor,
        h3DataOutdoorNo4g = h3DataOutdoorNo4g,
        h3DataIndoor = h3DataIndoor,
        h3DataIndoorNo4g = h3DataIndoorNo4g,

        o2VoiceOutdoor = o2VoiceOutdoor,
        o2VoiceOutdoorNo4g = o2VoiceOutdoorNo4g,
        o2VoiceIndoor = o2VoiceIndoor,
        o2VoiceIndoorNo4g = o2VoiceIndoorNo4g,
        o2DataOutdoor = o2DataOutdoor,
        o2DataOutdoorNo4g = o2DataOutdoorNo4g,
        o2DataIndoor = o2DataIndoor,
        o2DataIndoorNo4g = o2DataIndoorNo4g,

        voVoiceOutdoor = voVoiceOutdoor,
        voVoiceOutdoorNo4g = voVoiceOutdoorNo4g,
        voVoiceIndoor = voVoiceIndoor,
        voVoiceIndoorNo4g = voVoiceIndoorNo4g,
        voDataOutdoor = voDataOutdoor,
        voDataOutdoorNo4g = voDataOutdoorNo4g,
        voDataIndoor = voDataIndoor,
        voDataIndoorNo4g = voDataIndoorNo4g,

        signalTypeList = listOf(
            //Indoor Data
            ProviderSignalType(
                signalType = SignalType.DATA_INDOOR,
                providerSignalList = listOf(
                    //ee
                    ProviderSignal(
                        provider = Provider.EE,
                        fourG = eeDataIndoor,
                        nonFourG = eeDataIndoorNo4g
                    ),
                    //three
                    ProviderSignal(
                        provider = Provider.THREE,
                        fourG = h3DataIndoor,
                        nonFourG = h3DataIndoorNo4g
                    ),
                    //o2
                    ProviderSignal(
                        provider = Provider.O2,
                        fourG = o2DataIndoor,
                        nonFourG = o2DataIndoorNo4g
                    ),
                    //vo
                    ProviderSignal(
                        provider = Provider.VODAFONE,
                        fourG = voDataIndoor,
                        nonFourG = voDataIndoorNo4g
                    )
                )
            ),

            //OutDoor Data
            ProviderSignalType(
                signalType = SignalType.DATA_OUTDOOR,
                providerSignalList = listOf(
                    //ee
                    ProviderSignal(
                        provider = Provider.EE,
                        fourG = eeDataOutdoor,
                        nonFourG = eeDataOutdoorNo4g
                    ),
                    //three
                    ProviderSignal(
                        provider = Provider.THREE,
                        fourG = h3DataOutdoor,
                        nonFourG = h3DataOutdoorNo4g
                    ),
                    //o2
                    ProviderSignal(
                        provider = Provider.O2,
                        fourG = o2DataOutdoor,
                        nonFourG = o2DataOutdoorNo4g
                    ),
                    //vo
                    ProviderSignal(
                        provider = Provider.VODAFONE,
                        fourG = voDataOutdoor,
                        nonFourG = voDataOutdoorNo4g
                    )
                )
            ),

            //Indoor Voice
            ProviderSignalType(
                signalType = SignalType.VOICE_INDOOR,
                providerSignalList = listOf(
                    //ee
                    ProviderSignal(
                        provider = Provider.EE,
                        fourG = eeVoiceIndoor,
                        nonFourG = eeVoiceIndoorNo4g
                    ),
                    //three
                    ProviderSignal(
                        provider = Provider.THREE,
                        fourG = h3VoiceIndoor,
                        nonFourG = h3VoiceIndoorNo4g
                    ),
                    //o2
                    ProviderSignal(
                        provider = Provider.O2,
                        fourG = o2VoiceIndoor,
                        nonFourG = o2VoiceIndoorNo4g
                    ),
                    //vo
                    ProviderSignal(
                        provider = Provider.VODAFONE,
                        fourG = voVoiceIndoor,
                        nonFourG = voVoiceIndoorNo4g
                    )
                )
            ),

            //OutDoor Voice
            ProviderSignalType(
                signalType = SignalType.VOICE_OUTDOOR,
                providerSignalList = listOf(
                    //ee
                    ProviderSignal(
                        provider = Provider.EE,
                        fourG = eeVoiceOutdoor,
                        nonFourG = eeVoiceOutdoorNo4g
                    ),
                    //three
                    ProviderSignal(
                        provider = Provider.THREE,
                        fourG = h3VoiceOutdoor,
                        nonFourG = h3VoiceOutdoorNo4g
                    ),
                    //o2
                    ProviderSignal(
                        provider = Provider.O2,
                        fourG = o2VoiceOutdoor,
                        nonFourG = o2VoiceOutdoorNo4g
                    ),
                    //vo
                    ProviderSignal(
                        provider = Provider.VODAFONE,
                        fourG = voVoiceOutdoor,
                        nonFourG = voVoiceOutdoorNo4g
                    )
                )
            )
        )
    )
}