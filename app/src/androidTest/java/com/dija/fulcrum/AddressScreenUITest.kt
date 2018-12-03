package com.dija.fulcrum

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.ViewAction
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.action.ViewActions.typeText
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.espresso.matcher.ViewMatchers.withText
import android.support.test.rule.ActivityTestRule
import com.dija.fulcrum.adapter.ViewHolder
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class ChangeTextBehaviorTest {

    private lateinit var stringToBetyped: String

    @get:Rule
    var activityRule: ActivityTestRule<MainActivity>
            = ActivityTestRule(MainActivity::class.java)


    @Test
    fun inputAddressTest() {
        onView(withId(R.id.addressInputField)).perform(typeText("14 Ripon Road East York"))
    }

    @Test
    fun showWarningMessage() {
        onView(withId(R.id.confirmAddressNextButton)).perform(click())
        //Wait for a constant time of 2 seconds to get the response from server for login
        Thread.sleep(5000);
       // Assert.assertNotNull(onView(withText("Ok")))
        onView(withText("Ok"))
    }

    @Test
    fun completeAddressTest() {
        onView(withId(R.id.addressInputField)).perform(typeText("14 Ripon Road East York"))

        Thread.sleep(5000);

       onView(withId(R.id.addressSuggestionList))
            .perform(RecyclerViewActions.actionOnItemAtPosition<ViewHolder>(0, click()));

        Thread.sleep(2000);

        onView(withId(R.id.confirmAddressNextButton)).perform(click())
    }

}