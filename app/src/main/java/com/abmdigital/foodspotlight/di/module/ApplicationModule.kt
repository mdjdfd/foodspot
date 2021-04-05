package com.abmdigital.foodspotlight.di.module

import android.app.Application
import android.content.Context
import com.abmdigital.foodspotlight.ApplicationController
import com.abmdigital.foodspotlight.data.mockapi.JsonUtil
import com.abmdigital.foodspotlight.data.mockapi.MockApiHelper
import com.abmdigital.foodspotlight.data.model.User
import com.abmdigital.foodspotlight.data.mockapi.MockApiHelperImpl
import com.google.gson.Gson

import com.google.gson.reflect.TypeToken
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import java.lang.reflect.Type
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class ApplicationModule {


    @Singleton
    @Provides
    fun provideContext(application: Application): Context = application


    @Singleton
    @Provides
    fun provideApplication(@ApplicationContext app: Context): ApplicationController {
        return app as ApplicationController
    }

    @Provides
    @Singleton
    fun provideJsonUtil(applicationController: ApplicationController): JsonUtil =
        JsonUtil(applicationController)

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