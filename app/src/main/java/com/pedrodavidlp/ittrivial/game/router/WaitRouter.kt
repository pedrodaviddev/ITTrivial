package com.pedrodavidlp.ittrivial.game.router

import android.os.Handler
import com.pedrodavidlp.ittrivial.game.contract.WaitContract
import com.pedrodavidlp.ittrivial.game.view.activity.WaitActivity

class WaitRouter(val activity: WaitActivity) : WaitContract.Router {
  companion object {
    val RESULT_LEAVE: Int = 1000
  }

  override fun leaveGame() {
    activity.setResult(RESULT_LEAVE)
    activity.finish()
  }

  override fun goToGame() {
    Handler().postDelayed({
      activity.finish()
    }, 2000)
  }
}