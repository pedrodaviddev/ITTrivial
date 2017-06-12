package com.pedrodavidlp.ittrivial

import com.pedrodavidlp.ittrivial.game.data.FireGameRepository
import com.pedrodavidlp.ittrivial.game.data.FireQuestionRepository
import com.pedrodavidlp.ittrivial.game.data.MockGameRepository
import com.pedrodavidlp.ittrivial.game.domain.usecase.GetTurn
import com.pedrodavidlp.ittrivial.game.domain.usecase.LeaveGame
import com.pedrodavidlp.ittrivial.game.presenter.GamePresenter
import com.pedrodavidlp.ittrivial.game.presenter.QuestionPresenter
import com.pedrodavidlp.ittrivial.game.presenter.RoulettePresenter
import com.pedrodavidlp.ittrivial.game.presenter.WaitPresenter
import com.pedrodavidlp.ittrivial.game.router.GameRouter
import com.pedrodavidlp.ittrivial.game.router.QuestionRouter
import com.pedrodavidlp.ittrivial.game.router.RouletteRouter
import com.pedrodavidlp.ittrivial.game.router.WaitRouter
import com.pedrodavidlp.ittrivial.game.view.activity.GameActivity
import com.pedrodavidlp.ittrivial.game.view.activity.QuestionFragment
import com.pedrodavidlp.ittrivial.game.view.activity.RouletteFragment
import com.pedrodavidlp.ittrivial.game.view.activity.WaitFragment
import com.pedrodavidlp.ittrivial.login.data.FireLobbyRepository
import com.pedrodavidlp.ittrivial.login.domain.usecase.*
import com.pedrodavidlp.ittrivial.login.presenter.*
import com.pedrodavidlp.ittrivial.login.router.EnterGameRouter
import com.pedrodavidlp.ittrivial.login.router.GameListRouter
import com.pedrodavidlp.ittrivial.login.router.MenuRouter
import com.pedrodavidlp.ittrivial.login.router.UserListRouter
import com.pedrodavidlp.ittrivial.login.view.*

object ServiceLocator {

  //Repositories
  private val lobby = FireLobbyRepository()
  private val game = FireGameRepository()

  private fun provideGameRepository() = MockGameRepository()
  private fun provideQuestionRepository() = FireQuestionRepository()
  private fun provideFireGameRepository() = FireGameRepository()

  //Use Cases
  private fun provideGetGameListUseCase() = GetGameList(lobby)

  private fun provideNotifyStartGameUseCase(): NotifyStartGame = NotifyStartGame(lobby)
  private fun provideLeaveGameUseCase(): LeaveGame = LeaveGame(game)
  private fun provideEnterGameListUseCase() = EnterGame(lobby)
  private fun provideCreateGameUseCase() = CreateGame(lobby)
  private fun provideGetUserListUseCase() = GetUserList(lobby)
  private fun provideExitGameUseCase() = ExitGame(lobby)
  private fun provideStartGameUseCase() = StartGame(lobby)
  private fun provideGetTurnUseCase() = GetTurn(provideFireGameRepository())
  private fun provideSelectUsernameUseCase() = SelectUsername()

  //Routers
  private fun provideGameListRouter(activity: GameListActivity) = GameListRouter(activity)

  fun provideEnterGameRouter(activity: EnterGameActivity): EnterGameRouter = EnterGameRouter(activity)

  fun provideGameRouter(activity: GameActivity) = GameRouter(activity)

  private fun provideWaitRouter(fragment: WaitFragment): WaitRouter = WaitRouter(fragment)
  private fun provideUserListAdminRouter(activity: PlayerListAdminActivity) = UserListRouter(activity)
  private fun provideUserListGuestRouter(activity: PlayerListGuestActivity) = UserListRouter(activity)
  private fun provideRouletteRouter(fragment: RouletteFragment) = RouletteRouter(fragment)
  private fun provideQuestionRouter(fragment: QuestionFragment) = QuestionRouter(fragment)

  private fun provideMenuRouter(activity: MenuActivity) = MenuRouter(activity)


  //Presenters
  fun provideEnterGamePresenter() = EnterGamePresenter(provideSelectUsernameUseCase())

  fun provideGameListPresenter(activity: GameListActivity) = GameListPresenter(provideGetGameListUseCase(), provideEnterGameListUseCase(), provideGameListRouter(activity))
  fun provideMenuPresenter(activity: MenuActivity) = MenuPresenter(provideMenuRouter(activity), provideCreateGameUseCase())

  fun providePlayerListAdmintPresenter(activity: PlayerListAdminActivity) = UserListPresenter(
      provideGetUserListUseCase(),
      provideExitGameUseCase(),
      provideStartGameUseCase(),
      provideUserListAdminRouter(activity))

  fun providePlayerListGuestPresenter(activity: PlayerListGuestActivity) = UserListGuestPresenter(
      provideGetUserListUseCase(),
      provideExitGameUseCase(),
      provideStartGameUseCase(),
      provideNotifyStartGameUseCase(),
      provideUserListGuestRouter(activity))



  fun provideRoulettePresenter(fragment: RouletteFragment) = RoulettePresenter(provideGameRepository(), provideLeaveGameUseCase(), provideRouletteRouter(fragment))
  fun provideQuestionPresenter(fragment: QuestionFragment) = QuestionPresenter(provideQuestionRepository(), provideFireGameRepository(), provideQuestionRouter(fragment))
  fun provideWaitPresenter(fragment: WaitFragment) = WaitPresenter(provideGetTurnUseCase(), provideLeaveGameUseCase(), provideGetUserListUseCase(), provideWaitRouter(fragment))

  fun provideGamePresenter(activity: GameActivity): GamePresenter = GamePresenter(provideGameRouter(activity))


}