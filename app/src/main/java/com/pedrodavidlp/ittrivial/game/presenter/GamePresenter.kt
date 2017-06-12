package com.pedrodavidlp.ittrivial.game.presenter

import com.pedrodavidlp.ittrivial.game.router.GameRouter

class GamePresenter(private val router: GameRouter) {
  fun init(turn: Boolean) {
    if (turn) {
      router.goToRoulette()
    } else {
      router.goToWait()
    }
  }
}