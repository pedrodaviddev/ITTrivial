package com.pedrodavidlp.ittrivial.usecases

import junit.framework.Assert.assertTrue

class GetGameListShould {
  @org.mockito.Mock lateinit var repository: com.pedrodavidlp.ittrivial.login.domain.repository.LobbyRepository

  lateinit var testableGetGameList: com.pedrodavidlp.ittrivial.testable.TestableGetGameList
  val listWithFiveStartedGames =
      listOf(com.pedrodavidlp.ittrivial.game.domain.model.Game("Mock", 4, true),
          com.pedrodavidlp.ittrivial.game.domain.model.Game("Mock", 4, true),
          com.pedrodavidlp.ittrivial.game.domain.model.Game("Mock", 3, false),
          com.pedrodavidlp.ittrivial.game.domain.model.Game("Mock", 4, false),
          com.pedrodavidlp.ittrivial.game.domain.model.Game("Mock", 4, false),
          com.pedrodavidlp.ittrivial.game.domain.model.Game("Mock", 2, true),
          com.pedrodavidlp.ittrivial.game.domain.model.Game("Mock", 4, true),
          com.pedrodavidlp.ittrivial.game.domain.model.Game("Mock", 4, true),
          com.pedrodavidlp.ittrivial.game.domain.model.Game("Mock", 4, false)
      )
  val listWithThreeGamesWithSixPlayers =
      listOf(com.pedrodavidlp.ittrivial.game.domain.model.Game("Mock", 4, false),
          com.pedrodavidlp.ittrivial.game.domain.model.Game("Mock", 4, false),
          com.pedrodavidlp.ittrivial.game.domain.model.Game("Mock", 3, false),
          com.pedrodavidlp.ittrivial.game.domain.model.Game("Mock", 6, false),
          com.pedrodavidlp.ittrivial.game.domain.model.Game("Mock", 4, false),
          com.pedrodavidlp.ittrivial.game.domain.model.Game("Mock", 6, false),
          com.pedrodavidlp.ittrivial.game.domain.model.Game("Mock", 4, false),
          com.pedrodavidlp.ittrivial.game.domain.model.Game("Mock", 6, false),
          com.pedrodavidlp.ittrivial.game.domain.model.Game("Mock", 4, false)
      )

  @org.junit.Before
  fun setUp() {
    org.mockito.MockitoAnnotations.initMocks(this)
    testableGetGameList = com.pedrodavidlp.ittrivial.testable.TestableGetGameList(repository)
  }

  @org.junit.Test
  fun `Delete games with six players`() {
    val expected = listWithThreeGamesWithSixPlayers.size - 3
    assertTrue(testableGetGameList.testList(listWithThreeGamesWithSixPlayers).size == expected)
  }

  @org.junit.Test
  fun `Delete started games`() {
    val expected = listWithFiveStartedGames.size - 5
    assertTrue(testableGetGameList.testList(listWithFiveStartedGames).size == expected)
  }
}