package com.pedrodavidlp.ittrivial.game.router

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import com.pedrodavidlp.ittrivial.R
import com.pedrodavidlp.ittrivial.game.view.Category
import com.pedrodavidlp.ittrivial.game.view.activity.GameActivity
import com.pedrodavidlp.ittrivial.game.view.activity.QuestionFragment
import com.pedrodavidlp.ittrivial.game.view.activity.RouletteFragment
import com.pedrodavidlp.ittrivial.game.view.activity.WaitFragment

class GameRouter(private val activity: GameActivity) {
  fun goToRoulette() {
    this.updateFragment(RouletteFragment())
  }

  fun goToWait() {
    this.updateFragment(WaitFragment())
  }

  fun goToQuestion(category: Category) {
    this.updateFragment(QuestionFragment(category))
  }

  private fun updateFragment(fragment: Fragment) {
    val manager: FragmentManager = activity.supportFragmentManager
    val transaction: FragmentTransaction? = manager.beginTransaction()
    transaction?.replace(R.id.container, fragment)
    transaction?.commitAllowingStateLoss()
  }


}