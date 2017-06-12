package com.pedrodavidlp.ittrivial.usecases

import com.pedrodavidlp.ittrivial.game.domain.model.Player
import com.pedrodavidlp.ittrivial.login.domain.repository.LobbyRepository
import com.pedrodavidlp.ittrivial.testable.TestableGetPlayerList
import junit.framework.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class GetPlayerListShould {
  @Mock lateinit var repository: LobbyRepository
  lateinit var testableGetPlayerList: TestableGetPlayerList
  val listWithWinner =
      listOf(
          Player("loser", false, false, false, false, false, false),
          Player("loser1", false, false, false, false, false, false),
          Player("winner", false, true, true, true, true, true),
          Player("loser2", false, false, false, false, false, false),
          Player("loser3", false, false, false, false, false, false)
      )
  val listWithNoWinner =
      listOf(
          Player("loser", false, false, false, false, false, false),
          Player("loser1", false, false, false, false, false, false),
          Player("loser2", false, false, false, false, false, false),
          Player("loser3", false, false, false, false, false, false)
      )

  @Before
  fun setUp() {
    MockitoAnnotations.initMocks(this)
    testableGetPlayerList = TestableGetPlayerList(repository)
  }

  @Test
  fun `Return winner if exist`() {
    val winner = testableGetPlayerList.testList(listWithWinner)
    assertTrue(winner != null && winner.isWinner())
  }

  @Test
  fun `Return null if there s no winner`() {
    val winner = testableGetPlayerList.testList(listWithNoWinner)
    assertTrue(winner == null)
  }
}