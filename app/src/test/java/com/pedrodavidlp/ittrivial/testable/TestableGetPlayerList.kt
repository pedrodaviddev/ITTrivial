package com.pedrodavidlp.ittrivial.testable

class TestableGetPlayerList(repository: com.pedrodavidlp.ittrivial.login.domain.repository.LobbyRepository) : com.pedrodavidlp.ittrivial.game.domain.usecase.GetPlayerList(repository) {
  fun testList(list: List<com.pedrodavidlp.ittrivial.game.domain.model.Player>): com.pedrodavidlp.ittrivial.game.domain.model.Player? {
    return super.getWinnerPlayer(list)
  }
}