package com.pedrodavidlp.ittrivial

import com.pedrodavidlp.ittrivial.game.data.FireGameRepository
import com.pedrodavidlp.ittrivial.game.data.FireQuestionRepository
import com.pedrodavidlp.ittrivial.game.domain.usecase.GetTurn
import com.pedrodavidlp.ittrivial.game.domain.usecase.LeaveGame
import com.pedrodavidlp.ittrivial.game.presenter.GamePresenter
import com.pedrodavidlp.ittrivial.game.presenter.QuestionPresenter
import com.pedrodavidlp.ittrivial.game.presenter.RoulettePresenter
import com.pedrodavidlp.ittrivial.game.presenter.WaitPresenter
import com.pedrodavidlp.ittrivial.game.router.GameRouter
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
  private fun provideGameRepository() = FireGameRepository()
  private fun provideQuestionRepository() = FireQuestionRepository()
  private fun provideLobbyRepository() = FireLobbyRepository()

  //Use Cases
  private fun provideGetGameListUseCase() = GetGameList(provideLobbyRepository())

  private fun provideNotifyStartGameUseCase(): NotifyStartGame = NotifyStartGame(provideLobbyRepository())
  private fun provideLeaveGameUseCase(): LeaveGame = LeaveGame(provideGameRepository())
  private fun provideGetPlayerListUseCase(): GetPlayerList = GetPlayerList(provideLobbyRepository())
  private fun provideEnterGameListUseCase() = EnterGame(provideLobbyRepository())
  private fun provideCreateGameUseCase() = CreateGame(provideLobbyRepository())
  private fun provideGetUserListUseCase() = GetUserList(provideLobbyRepository())
  private fun provideExitGameUseCase() = ExitGame(provideLobbyRepository())
  private fun provideStartGameUseCase() = StartGame(provideLobbyRepository())
  private fun provideGetTurnUseCase() = GetTurn(provideGameRepository())
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
  fun provideEnterGamePresenter(activity: EnterGameActivity) = EnterGamePresenter(provideSelectUsernameUseCase(), provideEnterGameRouter(activity))

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
  fun provideQuestionPresenter(fragment: QuestionFragment) = QuestionPresenter(provideQuestionRepository(), provideGameRepository(), provideQuestionRouter(fragment))
  fun provideWaitPresenter(fragment: WaitFragment) = WaitPresenter(provideGetTurnUseCase(), provideLeaveGameUseCase(), provideGetPlayerListUseCase(), provideWaitRouter(fragment))


  fun provideGamePresenter(activity: GameActivity): GamePresenter = GamePresenter(provideGameRouter(activity))


}