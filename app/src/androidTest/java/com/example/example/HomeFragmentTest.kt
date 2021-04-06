package com.example.example

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.example.example.view.HomeActivity

@RunWith(AndroidJUnit4::class)
@LargeTest
class HomeFragmentTest {

    @get:Rule
    var activityRule: ActivityScenarioRule<HomeActivity>
            = ActivityScenarioRule(HomeActivity::class.java)

    @Test
    fun validateView() {
        onView(withId(R.id.recyclerView)).check(matches(isDisplayed()))
    }
}