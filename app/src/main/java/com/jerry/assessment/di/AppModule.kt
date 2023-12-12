package com.jerry.assessment.di


import android.app.Application
import androidx.room.Room
import com.jerry.assessment.BuildConfig
import com.jerry.assessment.common.utils.MoshiParser
import com.jerry.assessment.data.local.AssessmentDatabase
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor

import java.util.concurrent.TimeUnit
import javax.inject.Singleton
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory


const val TIME_OUT : Long = 10

@Module
@InstallIn(SingletonComponent::class)
object  AppModule {

    @Provides
    @Singleton
    fun provideMoshi(): Moshi {
        return Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    }

    fun provideMoshiParser(moshi: Moshi) = MoshiParser(moshi = moshi)


    @Singleton
    @Provides
    fun provideOKHttpClientLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
            if (!BuildConfig.DEBUG)
                level = HttpLoggingInterceptor.Level.NONE
        }
    }
    @Singleton
    @Provides
    fun provideOKHttpClientInterceptor(): Interceptor {
        return object: Interceptor {
            override fun intercept(chain: Interceptor.Chain): Response {

                val original = chain.request()

                val newRequest = original.newBuilder()
                    //.removeHeader("User-Agent")
                    //.addHeader("User-Agent", "other-user-agent")
                    .addHeader("Ocp-Apim-Subscription-Key", "${BuildConfig.KEY}")
                    //.addHeader("Accept-Encoding", "deflate")
                    .addHeader("Cache-Control", "no-cache")
                    //.addHeader("Content-Type","application/json")

                    .build()

                return chain.proceed(newRequest)
            }
        }
    }

    @Singleton
    @Provides
    fun provideOKHttpClient(logInterceptor: HttpLoggingInterceptor, interceptor: Interceptor): OkHttpClient {
        return  OkHttpClient.Builder()
            .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
            .readTimeout(TIME_OUT, TimeUnit.SECONDS)
            .addInterceptor(logInterceptor)
            .addInterceptor(interceptor)
            .build()
    }

    @Provides
    @Singleton
    fun provideAssessmentDatabase(app: Application): AssessmentDatabase {
        return Room.databaseBuilder(
            app,
            AssessmentDatabase::class.java,
            "assessment_db.db"
        ).build()
    }
}