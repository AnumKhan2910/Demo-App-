package com.example.demoapp.ui.productlist

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.example.demoapp.R
import com.example.demoapp.Utils
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.util.concurrent.TimeUnit

@RunWith(AndroidJUnit4ClassRunner::class)
class MainActivityTest {

    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun test_isActivityInView() {
        onView(withId(R.id.main_view)).check(matches(isDisplayed()))
    }

    @Test
    fun test_isRecyclerViewVisible() {
        onView(withId(R.id.recyclerView))
            .check(matches(withEffectiveVisibility(Visibility.VISIBLE)))
    }

    @Test
    fun test_isProgressBarVisible() {
        onView(withId(R.id.progressBar))
            .check(matches(withEffectiveVisibility(Visibility.VISIBLE)))
    }


    @Test
    fun test_NavigationToProductDetailScreen(){
        onView(isRoot()).perform(Utils.waitId(R.id.product_view,
            TimeUnit.SECONDS.toMillis(3000))).perform(click())

        onView(isRoot()).perform(Utils.waitId(R.id.product_view,
            TimeUnit.SECONDS.toMillis(3000))).check(matches(withEffectiveVisibility(Visibility.VISIBLE)))
    }


    @Test
    fun test_NavigationBackToMainScreen(){
        onView(isRoot()).perform(Utils.waitId(R.id.product_view,
            TimeUnit.SECONDS.toMillis(3000))).perform(click())

        CoroutineScope(kotlinx.coroutines.Dispatchers.IO).launch {
            delay(3000)

            CoroutineScope(kotlinx.coroutines.Dispatchers.Main).launch {

                onView(isRoot()).perform(Utils.waitId(R.id.detail_view,
                    TimeUnit.SECONDS.toMillis(3000))).check(matches(withEffectiveVisibility(Visibility.VISIBLE)))

                pressBack()

                onView(withId(R.id.main_view)).check(matches(isDisplayed()))
            }
        }
     }
}