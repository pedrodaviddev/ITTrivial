package com.pedrodavidlp.ittrivial.game.router

import android.os.Handler
import com.pedrodavidlp.ittrivial.game.contract.QuestionContract
import com.pedrodavidlp.ittrivial.game.view.activity.QuestionFragment
import com.pedrodavidlp.ittrivial.game.view.activity.WaitActivity
import org.jetbrains.anko.startActivity

class QuestionRouter(val fragment: QuestionFragment) : QuestionContract.Router {
  override fun goToGame() {
    Handler().postDelayed({
      fragment.finish()
    }, 500)
  }

  override fun goToWait() {
    Handler().postDelayed({
      fragment.startActivity<WaitActivity>()
      fragment.finish()
    }, 500)
  }
}