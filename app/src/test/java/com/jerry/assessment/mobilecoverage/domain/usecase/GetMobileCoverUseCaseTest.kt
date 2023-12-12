package com.jerry.assessment.features.mobilecoverage.domain.usecase

import com.jerry.assessment.base.usecase.UseCaseResult
import com.jerry.assessment.base.util.Resource
import com.jerry.assessment.features.mobilecoverage.MobileCoverageTestStubs
import com.jerry.assessment.features.mobilecoverage.data.mapper.toMobileAvailability
import com.jerry.assessment.features.mobilecoverage.domain.model.MobileAvailability
import com.jerry.assessment.features.mobilecoverage.domain.repository.MobileCoverageRepository
import io.mockk.coEvery
import io.mockk.junit5.MockKExtension
import io.mockk.mockkClass
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.net.SocketTimeoutException

@ExperimentalCoroutinesApi
@MockKExtension.ConfirmVerification
class GetMobileCoverUseCaseTest {

    private lateinit var mobileCoverageRepository: MobileCoverageRepository
    private lateinit var getMobileCoverUseCase: GetMobileCoverUseCase

    @BeforeEach
    fun setUp() {
        mobileCoverageRepository = mockkClass(MobileCoverageRepository::class)
        getMobileCoverUseCase = GetMobileCoverUseCase(
            mobileCoverageRepository = mobileCoverageRepository
        )
    }

    @Test
    fun `test GetMobileCoverUseCase invoke() return success state`() = runTest {
        //assign
        coEvery { mobileCoverageRepository.getMobileCoverageByPostCode(any()) } returns
                Resource.Success(
                    MobileCoverageTestStubs.testMobileCoverageSuccessResponse
                )
        //action
        val actual = getMobileCoverUseCase.invoke(postCode = MobileCoverageTestStubs.testPostCode)

        //verify
        Assertions.assertTrue(actual is UseCaseResult.Success)
        Assertions.assertEquals(
            MobileCoverageTestStubs.testMobileCoverageSuccessResponse.availabilities!!.size,
            (actual as UseCaseResult.Success<List<MobileAvailability>>).data.size,
        )
        Assertions.assertEquals(
            MobileCoverageTestStubs.testMobileCoverageSuccessResponse.availabilities!![0].toMobileAvailability(),
            (actual as UseCaseResult.Success<List<MobileAvailability>>).data[0]
        )
    }

    @Test
    fun `test GetMobileCoverUseCase invoke() return Failure status`() = runTest {
        //assign
        coEvery { mobileCoverageRepository.getMobileCoverageByPostCode(any())  }.throws(
            SocketTimeoutException()
        )

        //action
        val actual = getMobileCoverUseCase.invoke(postCode = MobileCoverageTestStubs.testPostCode)

        //verify
        Assertions.assertTrue(actual is UseCaseResult.Failure)
        Assertions.assertTrue(
            (actual as UseCaseResult.Failure).throwable is SocketTimeoutException
        )
    }
}