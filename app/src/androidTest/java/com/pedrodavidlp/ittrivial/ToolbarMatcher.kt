package com.pedrodavidlp.ittrivial

import android.os.Build
import android.support.annotation.RequiresApi
import android.support.test.espresso.ViewInteraction
import android.support.test.espresso.matcher.BoundedMatcher
import  android.support.v7.widget.Toolbar
import org.hamcrest.Description
import org.hamcrest.Matcher

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.assertion.ViewAssertions.doesNotExist
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.isAssignableFrom
import org.hamcrest.core.Is.`is`
import java.util.regex.Pattern.matches

object ToolbarMatcher {

  @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
  fun onToolbarWithTitle(title: CharSequence): ViewInteraction {
    return onView(isAssignableFrom(Toolbar::class.java)).check(matches(withToolbarTitle(`is`(title))))

  }

  @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
  private fun withToolbarTitle(textMatcher: Matcher<CharSequence>): Matcher<Any> {
    return object : BoundedMatcher<Any, Toolbar>(Toolbar::class.java) {
      public override fun matchesSafely(toolbar: Toolbar): Boolean {
        return textMatcher.matches(toolbar.title)
      }

      override fun describeTo(description: Description) {
        description.appendText("with toolbar title: ")
        textMatcher.describeTo(description)
      }
    }
  }
}