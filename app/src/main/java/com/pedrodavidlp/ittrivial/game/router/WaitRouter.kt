package com.pedrodavidlp.ittrivial.game.router

import android.os.Handler
import com.pedrodavidlp.ittrivial.game.contract.WaitContract
import com.pedrodavidlp.ittrivial.game.view.WaitActivity

class WaitRouter(val activity: WaitActivity) : WaitContract.Router {
  override fun goToGame() {
    Handler().postDelayed({
      activity.finish()
    }, 2000)
  }
}