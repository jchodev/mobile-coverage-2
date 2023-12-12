package com.jerry.assessment.features.mobilecoverage.presentation.viewmodel

import app.cash.turbine.test
import com.jerry.assessment.base.usecase.UseCaseResult
import com.jerry.assessment.features.mobilecoverage.MobileCoverageTestStubs
import com.jerry.assessment.features.mobilecoverage.MobileCoverageTestStubs.Companion.testMobileAvailabilityDto
import com.jerry.assessment.features.mobilecoverage.data.mapper.toMobileAvailability
import com.jerry.assessment.features.mobilecoverage.domain.usecase.GetMobileCoverUseCase
import com.jerry.assessment.features.mobilecoverage.presentation.mvi.MobileCoverageIntent
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.mockkClass
import io.mockk.mockkStatic
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi

import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.jupiter.api.AfterEach

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import timber.log.Timber
import java.net.SocketTimeoutException

@OptIn(ExperimentalCoroutinesApi::class)
class MobileCoverageViewModelTest {
    private lateinit var viewModel : MobileCoverageViewModel
    private lateinit var useCase: GetMobileCoverUseCase

    @BeforeEach
    fun setUp() {
        Dispatchers.setMain(StandardTestDispatcher())
        useCase = mockkClass(GetMobileCoverUseCase::class)

        mockkStatic(Timber::class)

        viewModel = MobileCoverageViewModel(
            getMobileCoverUseCase = useCase
        )
        viewModel.initIntent()
    }

    @AfterEach
    fun after() {
        Dispatchers.resetMain()
        clearAllMocks()
    }

    @Test
    fun `test MobileCoverageViewModel postCodeState was assigned as expect`() = runTest {

        viewModel.postCodeState.test {
            //action
            viewModel.sendIntent(
                intent = MobileCoverageIntent.PostCodeValueChange(MobileCoverageTestStubs.testPostCode)
            )

            //verify
            assertEquals(null, awaitItem())

            assertEquals(MobileCoverageTestStubs.testPostCode, awaitItem())

        }
    }

    @Test
    fun `test MobileCoverageViewModel list of MobileAvailability state expected success state`() = runTest {
        val testMobileAvailability = testMobileAvailabilityDto.toMobileAvailability()
        val useCaseResult = UseCaseResult.Success(
            listOf(
                testMobileAvailability
            )
        )

        //assign
        coEvery { useCase.invoke(any()) } returns useCaseResult


        viewModel.mobileAvailabilitiesState.test {
            //assign key
            viewModel.sendIntent(
                intent = MobileCoverageIntent.PostCodeValueChange(MobileCoverageTestStubs.testPostCode)
            )

            //action
            viewModel.sendIntent(
                intent = MobileCoverageIntent.SearchButtonClick
            )

            //verify
            assertEquals(null, awaitItem())

            val mobileAvailabilitiesStateData = awaitItem()
            assertEquals(true, mobileAvailabilitiesStateData != null )
            assertEquals( useCaseResult.data.size, mobileAvailabilitiesStateData!!.size )
            assertEquals( useCaseResult.data[0], mobileAvailabilitiesStateData!![0])

        }

    }

    @Test
    fun `test MobileCoverageViewModel list of MobileAvailability state expected no data assign`() = runTest {
        val useCaseResult = UseCaseResult.Failure(throwable = SocketTimeoutException())

        //assign
        coEvery { useCase.invoke(any()) } returns useCaseResult

        viewModel.mobileAvailabilitiesState.test {
            //assign key
            viewModel.sendIntent(
                intent = MobileCoverageIntent.PostCodeValueChange(MobileCoverageTestStubs.testPostCode)
            )

            //action
            viewModel.sendIntent(
                intent = MobileCoverageIntent.SearchButtonClick
            )

            //verify
            assertEquals(null, awaitItem())
        }

    }

    @Test
    fun `test MobileCoverageViewModel loadingState with success case`() = runTest{
        val testMobileAvailability = testMobileAvailabilityDto.toMobileAvailability()
        val useCaseResult = UseCaseResult.Success(
            listOf(
                testMobileAvailability
            )
        )

        //assign
        coEvery { useCase.invoke(any()) } returns useCaseResult


        viewModel.loadingState.test {
            //assign key
            viewModel.sendIntent(
                intent = MobileCoverageIntent.PostCodeValueChange(MobileCoverageTestStubs.testPostCode)
            )

            //action
            viewModel.sendIntent(
                intent = MobileCoverageIntent.SearchButtonClick
            )

            //verify
            assertEquals(false, awaitItem())
            assertEquals(true, awaitItem())
            assertEquals(false, awaitItem())
        }
    }

    @Test
    fun `test MobileCoverageViewModel loadingState with failed case`() = runTest{
        val useCaseResult = UseCaseResult.Failure(throwable = SocketTimeoutException())

        //assign
        coEvery { useCase.invoke(any()) } returns useCaseResult


        viewModel.loadingState.test {
            //assign key
            viewModel.sendIntent(
                intent = MobileCoverageIntent.PostCodeValueChange(MobileCoverageTestStubs.testPostCode)
            )

            //action
            viewModel.sendIntent(
                intent = MobileCoverageIntent.SearchButtonClick
            )

            //verify
            assertEquals(false, awaitItem())
            assertEquals(true, awaitItem())
            assertEquals(false, awaitItem())
        }
    }
}