package com.abmdigital.foodspotlight.ui.main.view

import com.abmdigital.foodspotlight.di.module.DataModule
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import dagger.hilt.components.SingletonComponent
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test


@UninstallModules(DataModule::class)
@HiltAndroidTest
class DataModuleTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)


    @Before
    fun setUp() {
        hiltRule.inject()
    }

    @Test
    fun someTest() {
    }

    @After
    fun tearDown() {
    }


    @Module
    @InstallIn(SingletonComponent::class)
    class TestDataModule {
    }

}

