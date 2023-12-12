package com.jerry.assessment.features.testonly

import androidx.lifecycle.SavedStateHandle
import app.cash.turbine.test

import io.mockk.clearAllMocks
import io.mockk.every
import io.mockk.mockkClass
import io.mockk.mockkStatic
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import timber.log.Timber

@OptIn(ExperimentalCoroutinesApi::class)
class TestOnlyViewModelTest {

    private lateinit var viewModel : TestOnlyViewModel
    private lateinit var savedStateHandle: SavedStateHandle

    @BeforeEach
    fun setUp() {
        Dispatchers.setMain(StandardTestDispatcher())
        savedStateHandle = mockkClass(SavedStateHandle::class)

        mockkStatic(Timber::class)

        //assign
        val savedStateHandle = SavedStateHandle().apply {
            set("name", "")
        }

        viewModel = TestOnlyViewModel(
            savedStateHandle = savedStateHandle
        )

    }

    @AfterEach
    fun after() {
        Dispatchers.resetMain()
        clearAllMocks()
    }

//    @Test /* TODO it should be test with Jetpack UI test !*/
//    fun `test TestOnlyViewModelTest postCodeState was assigned as expect`() = runTest {
//        viewModel.assignName("John")
//
//        // Assert the expected values
//        assertEquals("John", viewModel.name)
//    }

    @Test
    fun `test TestOnlyViewModelTest MutableStateFlow _nameState`() = runTest {

        viewModel.nameState.test {

            viewModel.assignNameState("John")

            //verify
            assertEquals("", awaitItem())
            assertEquals("John", awaitItem())
        }

    }


    @Test
    fun `test TestOnlyViewModelTest nameWithSaveState`() = runTest {

        //assign
        viewModel.assignNameWithSaveState("John")

        assertEquals("John", viewModel.nameWithSaveState.value)

    }
}