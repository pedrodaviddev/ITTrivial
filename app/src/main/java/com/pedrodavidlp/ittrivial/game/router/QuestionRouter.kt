package com.pedrodavidlp.ittrivial.game.router

import android.os.Handler
import com.pedrodavidlp.ittrivial.game.contract.QuestionContract
import com.pedrodavidlp.ittrivial.game.view.activity.QuestionActivity
import com.pedrodavidlp.ittrivial.game.view.activity.WaitActivity
import org.jetbrains.anko.startActivity

class QuestionRouter(val activity: QuestionActivity) : QuestionContract.Router {
  override fun goToGame() {
    Handler().postDelayed({
      activity.finish()
    }, 500)
  }

  override fun goToWait() {
    Handler().postDelayed({
      activity.startActivity<WaitActivity>()
      activity.finish()
    }, 500)
  }
}