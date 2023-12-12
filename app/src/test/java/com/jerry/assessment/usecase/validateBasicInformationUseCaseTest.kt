package com.jerry.assessment.features.loginreg.domain.usecase


import com.jerry.assessment.features.loginreg.presentation.provider.LocalizedProvider
import io.mockk.every
import io.mockk.junit5.MockKExtension
import io.mockk.mockkClass
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

@ExperimentalCoroutinesApi
@MockKExtension.ConfirmVerification
class validateBasicInformationUseCaseTest {

    private lateinit var validateBasicInformationUseCase: ValidateBasicInformationUseCase
    private lateinit var localizedProvider: LocalizedProvider

    @BeforeEach
    fun setUp() {
        localizedProvider = mockkClass(LocalizedProvider::class)
        validateBasicInformationUseCase = ValidateBasicInformationUseCase(
            localizedProvider = localizedProvider
        )
    }

    @Test
    fun `test ValidateLoginUseCaseTest validateEmail success`() {
        val testEmail = "hello@hello.com"
        val actual = validateBasicInformationUseCase.validateEmail(testEmail)

        Assertions.assertTrue(actual == null)
    }

    @Test
    fun `test ValidateLoginUseCaseTest validateEmail is null`() {
        val blankError = "blankError"

        //assign
        every { localizedProvider.getCannotBlankErrorMessage() } returns blankError
        val actual = validateBasicInformationUseCase.validateEmail("")

        Assertions.assertTrue(actual == blankError)
    }
}