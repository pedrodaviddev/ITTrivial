package com.pedrodavidlp.ittrivial.game.router

import android.os.Handler
import com.pedrodavidlp.ittrivial.game.contract.RouletteContract
import com.pedrodavidlp.ittrivial.game.view.Category
import com.pedrodavidlp.ittrivial.game.view.activity.QuestionFragment
import com.pedrodavidlp.ittrivial.game.view.activity.RouletteFragment
import com.pedrodavidlp.ittrivial.game.view.activity.WaitActivity
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.startActivityForResult

class RouletteRouter(val activity: RouletteFragment) : RouletteContract.Router {

  override fun goToQuestion(category: Category) {
    Handler().postDelayed({
      activity.startActivity<QuestionFragment>(Pair("Category", category))
    }, 1000)
  }

  override fun goToMenu() {
    activity.finish()
  }

  override fun goToWait() {
    activity.startActivityForResult<WaitActivity>(2)
  }
}