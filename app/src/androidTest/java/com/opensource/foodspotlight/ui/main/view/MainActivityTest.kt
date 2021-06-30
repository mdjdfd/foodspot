package com.opensource.foodspotlight.ui.main.view

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.rule.ActivityTestRule
import com.opensource.foodspotlight.R
import com.opensource.foodspotlight.di.module.DataModule
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@UninstallModules(DataModule::class)
@HiltAndroidTest
class MainActivityTest {

    @get:Rule(order = 0)
    var hiltRule = HiltAndroidRule(this)


    @get:Rule(order = 1)
    val activityTestRule = ActivityTestRule(MainActivity::class.java)


//
//    @get:Rule
//    val intentTestRule = IntentsTestRule(MainActivity::class.java)


    @Before
    fun setUp() {
        hiltRule.inject()
    }

    @Test
    fun mainActivityTest() {
        onView(withId(R.id.nav_view)).check(matches(isDisplayed()))
        onView(withId(R.id.nav_host_fragment)).check(matches(isDisplayed()))
        onView(withId(R.id.nav_view)).perform(click())
    }

}