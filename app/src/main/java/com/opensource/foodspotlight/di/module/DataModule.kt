package com.opensource.foodspotlight.di.module

import android.app.Application
import android.content.Context
import com.opensource.foodspotlight.ApplicationController
import com.opensource.foodspotlight.data.mockapi.JsonUtil
import com.opensource.foodspotlight.data.mockapi.MockApiHelper
import com.opensource.foodspotlight.data.model.User
import com.opensource.foodspotlight.data.mockapi.MockApiHelperImpl
import com.google.gson.Gson

import com.google.gson.reflect.TypeToken
import com.opensource.foodspotlight.BuildConfig
import com.opensource.foodspotlight.data.api.ApiHelper
import com.opensource.foodspotlight.data.api.ApiHelperImpl
import com.opensource.foodspotlight.data.api.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.reflect.Type
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DataModule {


//    @Provides
//    fun provideBaseUrl() = BuildConfig.CURRENCY_BASE_URL + BuildConfig.API_VERSION

    @Singleton
    @Provides
    fun provideApplication(@ApplicationContext app: Context): ApplicationController {
        return app as ApplicationController
    }

    @Singleton
    @Provides
    fun provideContext(application: Application): Context = application


    @Provides
    @Singleton
    fun provideOkHttpClient() = if (BuildConfig.DEBUG) {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        OkHttpClient.Builder().addInterceptor(loggingInterceptor).build()
    } else {
        OkHttpClient.Builder().build()
    }


    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder().addConverterFactory(GsonConverterFactory.create()).baseUrl("https://api.nomics.com/v1/")
            .client(okHttpClient).build()


    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)

    @Provides
    @Singleton
    fun provideApiHelper(apiHelper: ApiHelperImpl): ApiHelper = apiHelper





    @Provides
    @Singleton
    fun provideJsonUtil(appContext: Context): JsonUtil =
        JsonUtil(appContext)

    @Provides
    @Singleton
    fun provideGson(): Gson = Gson()

    @Provides
    @Singleton
    fun provideJson(jsonUtil: JsonUtil): String = jsonUtil.getJson()

    @Provides
    @Singleton
    fun provideType(): Type = object : TypeToken<List<User>>() {}.type

    @Provides
    @Singleton
    fun provideJsonConverter(gson: Gson, type: Type, json: String): MockApiHelperImpl =
        MockApiHelperImpl(
            gson.fromJson(
                json,
                type
            )
        )

    @Provides
    @Singleton
    fun provideData(mockApiHelperImpl: MockApiHelperImpl): MockApiHelper = mockApiHelperImpl


}