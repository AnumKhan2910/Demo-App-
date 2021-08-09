package com.example.demoapp.ui.productdetails

import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.example.demoapp.R
import org.junit.Rule
import org.junit.Test

import org.junit.runner.RunWith
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import com.example.demoapp.Utils
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit

@RunWith(AndroidJUnit4ClassRunner::class)
class ProductDetailsActivityTest{

    @get:Rule
    val activityRule = ActivityScenarioRule(ProductDetailsActivity::class.java)

    @Test
    fun test_isActivityInView() {
        CoroutineScope(Dispatchers.IO).launch {
            delay(3000)

            CoroutineScope(Dispatchers.Main).launch {

                onView(withId(R.id.detail_view)).check(matches(isDisplayed()))
            }
        }
    }

    @Test
    fun test_isTitleTextViewVisible() {
        CoroutineScope(Dispatchers.IO).launch {
            delay(3000)

            CoroutineScope(Dispatchers.Main).launch {

                onView(withId(R.id.titleText))
                    .check(matches(
                        withEffectiveVisibility(
                            Visibility.VISIBLE
                        )
                    ))
            }
        }
      }

    @Test
    fun test_isSubTitleTextViewVisible() {
        CoroutineScope(Dispatchers.IO).launch {
            delay(3000)

            CoroutineScope(Dispatchers.Main).launch {

                onView(withId(R.id.subTitleText))
                    .check(matches(
                        withEffectiveVisibility(
                            Visibility.VISIBLE
                        )
                    ))
            }
        }   }


    @Test
    fun test_isPriceTextViewVisible() {
        CoroutineScope(Dispatchers.IO).launch {
            delay(3000)

            CoroutineScope(Dispatchers.Main).launch {

                onView(withId(R.id.priceText))
                    .check(matches(
                        withEffectiveVisibility(
                            Visibility.VISIBLE
                        )
                    ))
            }
        }
    }

    @Test
    fun test_isDescriptionTextViewVisible() {
        CoroutineScope(Dispatchers.IO).launch {
            delay(3000)

            CoroutineScope(Dispatchers.Main).launch {

                onView(withId(R.id.descriptionText))
                    .check(matches(
                        withEffectiveVisibility(
                            Visibility.VISIBLE
                        )
                    ))
            }
        }    }

}