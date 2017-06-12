package com.pedrodavidlp.ittrivial.game.router

import android.os.Handler
import android.os.Looper
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import com.pedrodavidlp.ittrivial.R
import com.pedrodavidlp.ittrivial.game.domain.model.Player
import com.pedrodavidlp.ittrivial.game.view.Category
import com.pedrodavidlp.ittrivial.game.view.activity.GameActivity
import com.pedrodavidlp.ittrivial.game.view.activity.QuestionFragment
import com.pedrodavidlp.ittrivial.game.view.activity.RouletteFragment
import com.pedrodavidlp.ittrivial.game.view.activity.WaitFragment

class GameRouter(private val activity: GameActivity) {
  fun goToRoulette() {
    Handler().postDelayed({
      this.updateFragment(RouletteFragment())
    }, 400)
  }

  fun goToWait() {
    Handler(Looper.getMainLooper()).postDelayed({
      this.updateFragment(WaitFragment())
    }, 400)
  }

  fun goToQuestion(category: Category) {
    this.updateFragment(QuestionFragment(category))
  }

  fun goToFinish(player: Player) {

  }

  private fun updateFragment(fragment: Fragment) {
    val manager: FragmentManager = activity.supportFragmentManager
    val transaction: FragmentTransaction? = manager.beginTransaction()
    transaction?.replace(R.id.container, fragment)
    transaction?.commitAllowingStateLoss()
  }


}