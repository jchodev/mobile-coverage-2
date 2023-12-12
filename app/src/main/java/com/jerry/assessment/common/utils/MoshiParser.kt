package com.jerry.assessment.common.utils

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import java.lang.reflect.Type
import javax.inject.Inject

class MoshiParser @Inject constructor(
    private val moshi: Moshi
) {

    fun <T> fromJson(json: String, type: Type): T? {
        //use jsonAdapter<T> for generic adapter
        val jsonAdapter: JsonAdapter<T> = moshi.adapter(type)
        return jsonAdapter.fromJson(json)

    }

    fun <T> toJson(obj: T, type: Type): String? {
        //use jsonAdapter<T> for generic adapter
        val jsonAdapter: JsonAdapter<T> = moshi.adapter(type)

        return jsonAdapter.toJson(obj)
    }

}