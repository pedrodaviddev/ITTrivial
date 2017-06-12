package com.pedrodavidlp.ittrivial.testable

import com.pedrodavidlp.ittrivial.game.domain.model.Game
import com.pedrodavidlp.ittrivial.login.domain.repository.LobbyRepository
import com.pedrodavidlp.ittrivial.login.domain.usecase.GetGameList

class TestableGetGameList(repository: LobbyRepository) : GetGameList(repository) {
  fun testList(list: List<Game>): List<Game> {
    var res = super.showOnlyNotStartedGames(list)
    res = super.showOnlyWithLessThanSixPlayers(res)
    return res
  }
}