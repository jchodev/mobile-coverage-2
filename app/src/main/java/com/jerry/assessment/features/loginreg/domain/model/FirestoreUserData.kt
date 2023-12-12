package com.jerry.assessment.features.loginreg.domain.model


data class FirestoreUserData(
    val email: String = "",
    val firstName: String = "",
    val lastName: String = "",

    val other: String = "" // like token ...
)