package com.example.quarntine.ui

import android.content.Intent
import org.junit.Rule
import org.junit.Test
import com.example.quarntine.R
import org.hamcrest.Matchers
import org.junit.runner.RunWith
import androidx.recyclerview.widget.RecyclerView
import android.view.View
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.BoundedMatcher
import androidx.test.espresso.matcher.RootMatchers
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import kotlinx.android.synthetic.main.activity_main.*
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.junit.Assert
import org.junit.Before


@Suppress("DEPRECATION")
@RunWith(AndroidJUnit4::class)
class MainActivityTest {
    @get:Rule
    var rule = ActivityScenarioRule(MainActivity::class.java)


    @Test
    fun duck() {
        Espresso.onView(ViewMatchers.withId(R.id.movie_list_recyclerview))
            .check(ViewAssertions.matches(ViewMatchers.withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)))


        onView(withId(R.id.movie_list_recyclerview)).check(matches(isDisplayed()));


    }


}