package com.pedrodavidlp.ittrivial

import android.support.test.espresso.Espresso
import android.support.test.espresso.action.ViewActions
import android.support.test.espresso.assertion.ViewAssertions
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.view.WindowManager
import com.pedrodavidlp.ittrivial.login.view.EnterGameActivity
import junit.framework.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import android.widget.TextView
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.core.deps.guava.base.Predicates.instanceOf
import android.support.test.espresso.matcher.ViewMatchers.*
import java.util.EnumSet.allOf
import java.util.regex.Pattern.matches
import android.support.test.espresso.matcher.ViewMatchers.withParent
import android.support.test.espresso.Espresso.onView
import android.widget.Toolbar
import android.support.test.espresso.Espresso.onView
import junit.framework.Assert.assertFalse


@RunWith(AndroidJUnit4::class)
class GameTest {
  val USERNAME_TO_BE_TYPED = "Conrado"

  @Rule @JvmField
  val activity = ActivityTestRule<EnterGameActivity>(EnterGameActivity::class.java)


  @Test
  fun testWhenUserJoinGameShouldBeAtPlayerList() {
    enterToGame()
    Espresso.onView(ViewMatchers.withId(R.id.joinMatchButton)).perform(ViewActions.click())
    ToolbarMatcher.onToolbarWithTitle("Lista de partidas")
  }

  @Test
  fun testWhenUserIsInEnterGameScreen() {
    enterToGame()
    ToolbarMatcher.onToolbarWithTitle("ITTrivial")
  }

  fun enterToGame(){
    Espresso.onView(ViewMatchers.withId(R.id.username))
        .perform(ViewActions.typeText(USERNAME_TO_BE_TYPED), ViewActions.closeSoftKeyboard())
    Espresso.onView(ViewMatchers.withId(R.id.playButton)).perform(ViewActions.click())

  }

}