package com.jerry.assessment.features.loginreg.data.mapper

import com.jerry.assessment.features.loginreg.domain.model.FirestoreUserData

fun FirestoreUserData.toMap(): Map<String, Any>{
    return mapOf(
        "email" to email,
        "firstName" to firstName,
        "lastName" to lastName,
        "other" to lastName
    )
}

fun Map<String, Any>.toFirestoreUserData(): FirestoreUserData{
    return FirestoreUserData(
        email = this["email"] as? String ?: "",
        firstName = this["firstName"] as? String ?: "",
        lastName = this["lastName"] as? String ?: "",
        other = this["other"] as? String ?: ""
    )
}
