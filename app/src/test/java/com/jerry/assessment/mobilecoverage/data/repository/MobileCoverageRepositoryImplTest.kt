package com.jerry.assessment.features.mobilecoverage.data.repository

import com.jerry.assessment.base.util.Resource
import com.jerry.assessment.data.local.AssessmentDatabase
import com.jerry.assessment.features.mobilecoverage.MobileCoverageTestStubs
import com.jerry.assessment.features.mobilecoverage.data.local.dao.AvailabilityEntityDao
import com.jerry.assessment.features.mobilecoverage.data.local.dao.PostCodeEntityDao
import com.jerry.assessment.features.mobilecoverage.data.local.entity.AvailabilityEntity
import com.jerry.assessment.features.mobilecoverage.data.local.entity.PostCodeEntity
import com.jerry.assessment.features.mobilecoverage.data.local.entity.PostCodeWithAvailabilities
import com.jerry.assessment.features.mobilecoverage.data.mapper.toAvailabilityEntity
import com.jerry.assessment.features.mobilecoverage.data.mapper.toMobileAvailability

import com.jerry.assessment.features.mobilecoverage.data.remote.api.MobileCoverageApi
import com.jerry.assessment.features.mobilecoverage.data.remote.response.MobileCoverageErrorResponse
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.every

import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.mockkClass
import io.mockk.unmockkAll
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest

import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody

import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import retrofit2.Converter

import retrofit2.Response
import retrofit2.Retrofit

@ExperimentalCoroutinesApi
@MockKExtension.ConfirmVerification
class MobileCoverageRepositoryImplTest {

    private lateinit var retrofit: Retrofit
    private lateinit var mobileCoverageApi: MobileCoverageApi
    private lateinit var repository: MobileCoverageRepositoryImpl
    private lateinit var db: AssessmentDatabase
    private lateinit var postCodeDao: PostCodeEntityDao
    private lateinit var availabilityEntityDao: AvailabilityEntityDao

    @MockK
    private lateinit var mobileCoverageErrorResponseConverter : Converter<ResponseBody, MobileCoverageErrorResponse>

    @BeforeEach
    fun setUp() {
        mobileCoverageApi =  mockkClass(MobileCoverageApi::class)
        retrofit = mockkClass(Retrofit::class)
        db = mockkClass(AssessmentDatabase::class)
        postCodeDao = mockkClass(PostCodeEntityDao::class)
        availabilityEntityDao = mockkClass(AvailabilityEntityDao::class)

        every { db.postCodeEntityDao } returns postCodeDao
        every { db.availabilityEntityDao } returns availabilityEntityDao

        repository = MobileCoverageRepositoryImpl(
            mobileCoverageApi = mobileCoverageApi,
            retrofit = retrofit,
            db = db
        )

        MockKAnnotations.init(this)
    }

    @AfterEach
    fun tearDown() {
        unmockkAll()
    }

    @Test
    fun `test MobileCoverageRepositoryImpl getMobileCoverageByPostCode() success`() {

        val successResponse = MobileCoverageTestStubs.testMobileCoverageSuccessResponse

        runTest {

            //assign
            coEvery { postCodeDao.getPostCodeWithAvailability(any()) } returns null
            coEvery { mobileCoverageApi.getMobileCoverageByPostCode(any()) } returns Response.success(successResponse)

            //action
            val actual = repository.getMobileCoverageByPostCode(postCode = MobileCoverageTestStubs.testPostCode)

            //verify
            Assertions.assertEquals(true, actual.data != null )
            Assertions.assertEquals(successResponse.availabilities!!.size, actual.data!!.availabilities!!.size)
            Assertions.assertEquals(successResponse.availabilities!![0], actual.data!!.availabilities!![0])
        }
    }

    @Test
    fun `test MobileCoverageRepositoryImpl getMobileCoverageByPostCode2() return from DB success`() {

        val successResponse = MobileCoverageTestStubs.testMobileCoverageSuccessResponse

        runTest {

            //assign
            coEvery { postCodeDao.getPostCodeWithAvailability(MobileCoverageTestStubs.testPostCode) } returns listOf(
                PostCodeWithAvailabilities(
                    postCodeEntity = PostCodeEntity(
                        postCode =  MobileCoverageTestStubs.testPostCode
                    ),
                    availabilities = successResponse.availabilities!!.map {
                        it.toAvailabilityEntity(1L)
                    }
                )
            )
            //if don't add this two line, will be error!!
            coEvery { postCodeDao.insert(any()) } returns 1L
            coEvery { availabilityEntityDao.insert(any()) } returns 1L

            coEvery { mobileCoverageApi.getMobileCoverageByPostCode(any()) } returns Response.success(successResponse)

            //action
            val actual = repository.getMobileCoverageByPostCode2(postCode = MobileCoverageTestStubs.testPostCode)

            //verify
            Assertions.assertTrue(actual is Resource.Success)
            Assertions.assertEquals(true, actual.data != null)
            Assertions.assertEquals(1, actual.data!!.size)
        }
    }


    @Test
    fun `test MobileCoverageRepositoryImpl getMobileCoverageByPostCode() return error by throws exception`() {
        runTest {
            //assign
            coEvery { postCodeDao.getPostCodeWithAvailability(any()) } returns null
            coEvery { mobileCoverageApi.getMobileCoverageByPostCode(any()) } throws MobileCoverageTestStubs.Companion.FakeError

            //action
            val actual = repository.getMobileCoverageByPostCode(postCode = MobileCoverageTestStubs.testPostCode)

            //verify
            Assertions.assertTrue(
                actual is Resource.Error
            )
        }
    }

    @Test
    fun `test MobileCoverageRepositoryImpl getMobileCoverageByPostCode() return error by serverError`() {

        runTest {
            coEvery { postCodeDao.getPostCodeWithAvailability(any()) } returns null
            coEvery { retrofit.responseBodyConverter<MobileCoverageErrorResponse>(any(), any()) } returns mobileCoverageErrorResponseConverter
            coEvery { mobileCoverageErrorResponseConverter.convert(any()) } returns MobileCoverageErrorResponse(
                error = MobileCoverageTestStubs.testErrorMessage
            )

            //assign
            coEvery { mobileCoverageApi.getMobileCoverageByPostCode(any()) } returns Response.error(
                400,
                    ResponseBody.create("text/plain".toMediaTypeOrNull(), "msg"
                )
            )

            //action
            val actual = repository.getMobileCoverageByPostCode(postCode = MobileCoverageTestStubs.testPostCode)

            //verify
            Assertions.assertTrue(
                actual is Resource.Error
            )

            Assertions.assertEquals(
                MobileCoverageTestStubs.testErrorMessage,
                (actual as Resource.Error<MobileCoverageErrorResponse>).message
            )
        }
    }

}