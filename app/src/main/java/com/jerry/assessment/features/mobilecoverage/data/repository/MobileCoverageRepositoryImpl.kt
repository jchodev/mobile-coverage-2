package com.jerry.assessment.features.mobilecoverage.data.repository

import com.jerry.assessment.base.data.repository.BaseRepositoryImpl
import com.jerry.assessment.base.util.Resource
import com.jerry.assessment.data.local.AssessmentDatabase

import com.jerry.assessment.features.mobilecoverage.data.local.entity.PostCodeEntity
import com.jerry.assessment.features.mobilecoverage.data.mapper.toAvailabilityEntity
import com.jerry.assessment.features.mobilecoverage.data.mapper.toMobileAvailability
import com.jerry.assessment.features.mobilecoverage.data.remote.api.MobileCoverageApi
import com.jerry.assessment.features.mobilecoverage.data.remote.dto.MobileAvailabilityDto
import com.jerry.assessment.features.mobilecoverage.data.remote.response.MobileCoverageErrorResponse
import com.jerry.assessment.features.mobilecoverage.data.remote.response.MobileCoverageSuccessResponse
import com.jerry.assessment.features.mobilecoverage.domain.model.MobileAvailability
import com.jerry.assessment.features.mobilecoverage.domain.repository.MobileCoverageRepository
import retrofit2.Retrofit
import timber.log.Timber
import javax.inject.Inject

class MobileCoverageRepositoryImpl @Inject constructor(
    private val mobileCoverageApi: MobileCoverageApi,
    private val retrofit: Retrofit,
    private val db: AssessmentDatabase
): BaseRepositoryImpl(), MobileCoverageRepository {

    private val postCodeDao = db.postCodeEntityDao
    private val availabilityDao = db.availabilityEntityDao

    override suspend fun getMobileCoverageByPostCode2(postCode: String): Resource<List<MobileAvailability>> {
        val dbData = getMobileAvailabilitiesFromDB(postCode = postCode)
        if (!dbData.isNullOrEmpty()){
            Timber.d("MobileCoverageRepositoryImpl::getMobileCoverageByPostCode2::fromDB")
            return Resource.Success(dbData)
        }
        return try {

            val response = mobileCoverageApi.getMobileCoverageByPostCode(
                postCode = postCode
            )

            val serverError = response.errorBody()?.let {
                getErrorBodyAs(
                    errorBody = it,
                    retrofit = retrofit,
                    type = MobileCoverageErrorResponse ::class.java
                )
            }
            if (serverError?.error != null) {
                Resource.Error(serverError.error)
            } else if (response.body() != null && response.body()!!.availabilities?.isNotEmpty() == true) {

                //to DB
                insertPostCodeToDB(
                    postCode = postCode,
                    mobileAvailabilities = response.body()!!.availabilities!!
                )
                Timber.d("MobileCoverageRepositoryImpl::getMobileCoverageByPostCode2::fromServer")
                Resource.Success(
                    response.body()!!.availabilities!!.map {
                        it.toMobileAvailability()
                    }
                )

            } else if (response.body() != null) {
                //TODO
                Resource.Error("Some Error")
            }
            else {
                Resource.Error(response.message())
            }
        } catch (e: Exception){
            Resource.Error(e.message ?:  "unknown error")
        }
    }

    override suspend fun getMobileCoverageByPostCode(postCode: String): Resource<MobileCoverageSuccessResponse> {
        return try {
            val response = mobileCoverageApi.getMobileCoverageByPostCode(
                postCode = postCode
            )

            val serverError = response.errorBody()?.let {
                getErrorBodyAs(
                    errorBody = it,
                    retrofit = retrofit,
                    type = MobileCoverageErrorResponse ::class.java
                )
            }

            if (serverError?.error != null) {
                Resource.Error(serverError.error)
            } else if (response.body() != null) {
                Resource.Success(response.body()!!)
            } else {
                Resource.Error(response.message())
            }
        } catch (e: Exception){
            Resource.Error(e.message ?:  "unknown error")
        }
    }

    private suspend fun getMobileAvailabilitiesFromDB(postCode: String): List<MobileAvailability>?{
        val postCodes = postCodeDao.getPostCodeWithAvailability(
            postCode = postCode
        )
        postCodes?.let {

            if (postCodes.isNotEmpty()) {
                return it[0].availabilities.map {
                    it.toMobileAvailability(postCode)
                }
            }
        }
        return null
    }

    private suspend fun insertPostCodeToDB(postCode: String, mobileAvailabilities: List<MobileAvailabilityDto> ) {

        val postCodeEntity = PostCodeEntity(
            postCode = postCode
        )
        val postCodeEntityId = postCodeDao.insert(postCodeEntity)
        mobileAvailabilities.forEach {
            availabilityDao.insert(it.toAvailabilityEntity(postCodeEntityId))
        }

    }
}