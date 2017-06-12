package com.pedrodavidlp.ittrivial.base.domain.data

import com.pedrodavidlp.ittrivial.game.domain.model.Game
import com.pedrodavidlp.ittrivial.game.domain.model.Player

object Session {
  var player: Player = Player("No Username")
  lateinit var game: Game
}