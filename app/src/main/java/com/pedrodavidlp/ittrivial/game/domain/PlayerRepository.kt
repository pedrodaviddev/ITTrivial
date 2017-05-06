package com.pedrodavidlp.ittrivial.game.domain

import com.pedrodavidlp.ittrivial.game.contract.GameContract

interface PlayerRepository {
  fun getPlayersOnGame(game: Game, callback: GameContract.InteractorOutput)
}