package com.pedrodavidlp.ittrivial.game.router

import android.os.Handler
import com.pedrodavidlp.ittrivial.game.contract.QuestionContract
import com.pedrodavidlp.ittrivial.game.view.QuestionActivity
import com.pedrodavidlp.ittrivial.game.view.WaitActivity
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