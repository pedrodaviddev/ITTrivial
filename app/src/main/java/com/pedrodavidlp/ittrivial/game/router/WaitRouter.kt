package com.pedrodavidlp.ittrivial.game.router

import com.pedrodavidlp.ittrivial.game.contract.WaitContract
import com.pedrodavidlp.ittrivial.game.view.activity.WaitFragment

class WaitRouter(fragment: WaitFragment) : WaitContract.Router {
  companion object {
    val RESULT_LEAVE: Int = 1000
  }

}