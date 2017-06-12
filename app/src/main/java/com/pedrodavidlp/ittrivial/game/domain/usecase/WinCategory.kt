package com.pedrodavidlp.ittrivial.game.domain.usecase

import com.pedrodavidlp.ittrivial.base.domain.data.Session
import com.pedrodavidlp.ittrivial.game.domain.model.Game
import com.pedrodavidlp.ittrivial.game.domain.repository.GameRepository
import com.pedrodavidlp.ittrivial.game.view.Category
import kotlin.concurrent.thread

class WinCategory(private val repository: GameRepository) {
  fun winCategory(game: Game, username: String, category: Category) {
    thread {
      repository.winCategory(game, username, category)
    }
    Session.player.winCategory(category)
  }
}