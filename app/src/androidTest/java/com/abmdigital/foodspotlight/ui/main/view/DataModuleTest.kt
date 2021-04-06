package com.abmdigital.foodspotlight.ui.main.view

import android.app.Application
import android.content.Context
import com.abmdigital.foodspotlight.ApplicationController
import com.abmdigital.foodspotlight.data.mockapi.JsonUtil
import com.abmdigital.foodspotlight.data.mockapi.MockApiHelper
import com.abmdigital.foodspotlight.data.mockapi.MockApiHelperImpl
import com.abmdigital.foodspotlight.data.model.User
import com.abmdigital.foodspotlight.di.module.DataModule
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import dagger.hilt.components.SingletonComponent
import junit.framework.TestCase.assertNotNull
import junit.framework.TestCase.assertTrue
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.lang.reflect.Type
import javax.inject.Inject
import javax.inject.Singleton


@UninstallModules(DataModule::class)
@HiltAndroidTest
class DataModuleTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)


    @Inject
    lateinit var appContext: Context

    @Inject
    lateinit var gson: Gson

    @Inject
    lateinit var jsonString: String

    @Inject
    lateinit var typeToken: Type

    @Inject
    lateinit var mockApiHelperImpl: MockApiHelperImpl

    @Inject
    lateinit var mockApiHelper: MockApiHelper



    @Before
    fun setUp() {
        hiltRule.inject()
    }


    @Test
    fun testContext(){
        assertNotNull(appContext)
    }

    @Test
    fun testGson(){
        assertNotNull(gson)
    }

    @Test
    fun testJsonString(){
        assertNotNull(jsonString)
    }

    @Test
    fun testTypeToken() {
        assertNotNull(typeToken)
    }


    @Test
    fun testJsonConverter(){
        assertNotNull(mockApiHelperImpl)
    }

    @Test
    fun testData(){
        assertNotNull(mockApiHelper)
    }


    @After
    fun tearDown() {
    }


}



@Module
@InstallIn(SingletonComponent::class)
class TestDataModule {

    @Singleton
    @Provides
    fun testProvideContext(application: Application): Context = application


    @Provides
    @Singleton
    fun testProvideJsonUtil(applicationController: Context): JsonUtil =
        JsonUtil(applicationController)

    @Provides
    @Singleton
    fun testProvideGson(): Gson = Gson()

    @Provides
    @Singleton
    fun testProvideJson(jsonUtil: JsonUtil): String = jsonUtil.getJson()

    @Provides
    @Singleton
    fun testProvideType(): Type = object : TypeToken<List<User>>() {}.type

    @Provides
    @Singleton
    fun testProvideJsonConverter(gson: Gson, type: Type, json: String): MockApiHelperImpl =
        MockApiHelperImpl(
            gson.fromJson(
                json,
                type
            )
        )

    @Provides
    @Singleton
    fun testProvideData(mockApiHelperImpl: MockApiHelperImpl): MockApiHelper = mockApiHelperImpl
}

