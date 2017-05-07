package com.pedrodavidlp.ittrivial.game.domain.repository

interface PlayerRepository {
  fun getPlayersOnGame(game: com.pedrodavidlp.ittrivial.game.domain.model.Game, callback: com.pedrodavidlp.ittrivial.game.contract.GameContract.InteractorOutput)
}