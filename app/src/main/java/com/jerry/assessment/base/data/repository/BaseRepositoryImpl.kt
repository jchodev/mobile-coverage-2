package com.jerry.assessment.base.data.repository

import okhttp3.ResponseBody
import retrofit2.Retrofit
import java.io.IOException

open class BaseRepositoryImpl {

    @Throws(IOException::class)
    fun <T> getErrorBodyAs(errorBody: ResponseBody?, retrofit: Retrofit, type: Class<T>): T? {
        if (errorBody == null) {
            return null
        }
        val converter = retrofit.responseBodyConverter<T>(type,
            arrayOfNulls(0))
        return converter.convert(errorBody)
    }

}