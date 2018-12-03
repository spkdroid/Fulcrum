package com.dija.fulcrum

import android.support.test.espresso.Espresso
import android.support.test.espresso.action.ViewActions
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.rule.ActivityTestRule
import com.dija.fulcrum.adapter.ViewHolder
import org.junit.Before
import org.junit.Rule
import org.junit.Test


class InsuranceScreenUITest {


    private lateinit var stringToBetyped: String

    @get:Rule
    var activityRule: ActivityTestRule<MainActivity> = ActivityTestRule(MainActivity::class.java)

@Before
fun Init(){
    Espresso.onView(ViewMatchers.withId(R.id.addressInputField))
        .perform(ViewActions.typeText("14 Ripon Road East York"))

    Thread.sleep(5000);

    Espresso.onView(ViewMatchers.withId(R.id.addressSuggestionList))
        .perform(RecyclerViewActions.actionOnItemAtPosition<ViewHolder>(0, ViewActions.click()));

    Thread.sleep(2000);

    Espresso.onView(ViewMatchers.withId(R.id.confirmAddressNextButton)).perform(ViewActions.click())
}

    @Test
    fun inputInsuranceTest() {
        Espresso.onView(ViewMatchers.withId(R.id.insuranceInputField))
            .perform(ViewActions.typeText("AAA"))
    }

    @Test
    fun showWarningMessage() {
        Espresso.onView(ViewMatchers.withId(R.id.confirmInsuranceButton)).perform(ViewActions.click())
        //Wait for a constant time of 2 seconds to get the response from server for login
        Thread.sleep(5000);
        // Assert.assertNotNull(onView(withText("Ok")))
        Espresso.onView(ViewMatchers.withText("Ok"))
    }

    @Test
    fun showWrongInputMessage() {

        Espresso.onView(ViewMatchers.withId(R.id.insuranceInputField))
            .perform(ViewActions.typeText("AAAAAAAAAAAAAAAAAAAA"))

        Espresso.onView(ViewMatchers.withId(R.id.confirmInsuranceButton)).perform(ViewActions.click())
        //Wait for a constant time of 2 seconds to get the response from server for login
        Thread.sleep(5000);
        // Assert.assertNotNull(onView(withText("Ok")))
        Espresso.onView(ViewMatchers.withText("Ok"))
    }


    @Test
fun completeInsuranceTest(){
        Espresso.onView(ViewMatchers.withId(R.id.insuranceInputField))
            .perform(ViewActions.typeText("AAA"))

        Espresso.onView(ViewMatchers.withId(R.id.confirmInsuranceButton)).perform(ViewActions.click())
        //Wait for a constant time of 2 seconds to get the response from server for login
        Thread.sleep(5000);
        // Assert.assertNotNull(onView(withText("Ok")))
        Espresso.onView(ViewMatchers.withText("Ok"))
    }


}
