package com.pedrodavidlp.ittrivial


import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.*
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.espresso.matcher.ViewMatchers.withText
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.view.WindowManager
import com.pedrodavidlp.ittrivial.login.view.EnterGameActivity
import junit.framework.Assert.assertFalse
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class EnterGameTest {

  val USERNAME_TO_BE_TYPED = "Conrado"

  @Rule @JvmField
  val activity = ActivityTestRule<EnterGameActivity>(EnterGameActivity::class.java)

  @Before
  fun unlockScreen() {
    val activity = activity.getActivity()
    val wakeUpDevice = Runnable {
      activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON or
          WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED or
          WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
    }
    activity.runOnUiThread(wakeUpDevice)
  }

  @Test
  fun testWhenTypeUserAndEnterGame() {
    onView(withId(R.id.username))
        .perform(typeText(USERNAME_TO_BE_TYPED), closeSoftKeyboard())
    onView(withId(R.id.playButton)).perform(click())

    onView(withId(R.id.welcomeText)).check(matches(withText("Bienvenido $USERNAME_TO_BE_TYPED")))
  }

  @Test
  fun testWhenTextFieldIsEmptyAndEnterGame() {
    onView(withId(R.id.playButton)).perform(click())

    //Check if an activity is Finishing
    assertFalse(activity.getActivity().isFinishing());
  }

}

