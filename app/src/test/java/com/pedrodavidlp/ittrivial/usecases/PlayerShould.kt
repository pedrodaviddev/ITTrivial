package com.pedrodavidlp.ittrivial.usecases

import junit.framework.Assert.assertTrue

class PlayerShould {
  val loser = com.pedrodavidlp.ittrivial.game.domain.model.Player("Hola", false, false, false, false, false, false)
  val winner = com.pedrodavidlp.ittrivial.game.domain.model.Player("Hola", false, true, true, true, true, true)

  @org.junit.Test
  fun `Be winner if haves all categories`() {
    assertTrue(winner.isWinner())
  }

  @org.junit.Test
  fun `Be winner if win all categories`() {
    loser.winCategory(com.pedrodavidlp.ittrivial.game.view.Category.HARDWARE)
    loser.winCategory(com.pedrodavidlp.ittrivial.game.view.Category.SOFTWARE)
    loser.winCategory(com.pedrodavidlp.ittrivial.game.view.Category.ENTERPRISE)
    loser.winCategory(com.pedrodavidlp.ittrivial.game.view.Category.HISTORY)
    loser.winCategory(com.pedrodavidlp.ittrivial.game.view.Category.NETWORK)
    assertTrue(loser.isWinner())
  }
}