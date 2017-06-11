package com.pedrodavidlp.ittrivial

import com.pedrodavidlp.ittrivial.game.data.FireGameRepository
import com.pedrodavidlp.ittrivial.game.data.FireQuestionRepository
import com.pedrodavidlp.ittrivial.game.data.MockGameRepository
import com.pedrodavidlp.ittrivial.game.domain.usecase.GetTurn
import com.pedrodavidlp.ittrivial.game.domain.usecase.LeaveGame
import com.pedrodavidlp.ittrivial.game.presenter.GamePresenter
import com.pedrodavidlp.ittrivial.game.presenter.QuestionPresenter
import com.pedrodavidlp.ittrivial.game.presenter.WaitPresenter
import com.pedrodavidlp.ittrivial.game.router.GameRouter
import com.pedrodavidlp.ittrivial.game.router.QuestionRouter
import com.pedrodavidlp.ittrivial.game.router.WaitRouter
import com.pedrodavidlp.ittrivial.game.view.activity.GameActivity
import com.pedrodavidlp.ittrivial.game.view.activity.QuestionActivity
import com.pedrodavidlp.ittrivial.game.view.activity.WaitActivity
import com.pedrodavidlp.ittrivial.login.data.FireLobbyRepository
import com.pedrodavidlp.ittrivial.login.domain.usecase.*
import com.pedrodavidlp.ittrivial.login.presenter.EnterGamePresenter
import com.pedrodavidlp.ittrivial.login.presenter.GameListPresenter
import com.pedrodavidlp.ittrivial.login.presenter.MenuPresenter
import com.pedrodavidlp.ittrivial.login.presenter.UserListPresenter
import com.pedrodavidlp.ittrivial.login.router.GameListRouter
import com.pedrodavidlp.ittrivial.login.router.MenuRouter
import com.pedrodavidlp.ittrivial.login.router.UserListRouter
import com.pedrodavidlp.ittrivial.login.view.GameListActivity
import com.pedrodavidlp.ittrivial.login.view.MenuActivity
import com.pedrodavidlp.ittrivial.login.view.PlayerListAdminActivity
import com.pedrodavidlp.ittrivial.login.view.PlayerListGuestActivity

object ServiceLocator {

  //Repositories
  private val lobby = FireLobbyRepository()
  private val game = FireGameRepository()

  private fun provideGameRepository() = MockGameRepository()
  private fun provideQuestionRepository() = FireQuestionRepository()
  private fun provideFireGameRepository() = FireGameRepository()

  //Use Cases
  private fun provideGetGameListUseCase() = GetGameList(lobby)

  private fun provideLeaveGameUseCase(): LeaveGame = LeaveGame(game)
  private fun provideEnterGameListUseCase() = EnterGame(lobby)
  private fun provideCreateGameUseCase() = CreateGame(lobby)
  private fun provideGetUserListUseCase() = GetUserList(lobby)
  private fun provideExitGameUseCase() = ExitGame(lobby)
  private fun provideStartGameUseCase() = StartGame(lobby)
  private fun provideGetTurnUseCase() = GetTurn(provideFireGameRepository())

  //Routers
  private fun provideGameListRouter(activity: GameListActivity) = GameListRouter(activity)

  private fun provideWaitRouter(activity: WaitActivity): WaitRouter = WaitRouter(activity)

  private fun provideUserListAdminRouter(activity: PlayerListAdminActivity) = UserListRouter(activity)
  private fun provideUserListGuestRouter(activity: PlayerListGuestActivity) = UserListRouter(activity)
  private fun provideGameRouter(activity: GameActivity) = GameRouter(activity)
  private fun provideQuestionRouter(activity: QuestionActivity) = QuestionRouter(activity)
  private fun provideMenuRouter(activity: MenuActivity) = MenuRouter(activity)

  //Presenters
  fun provideEnterGamePresenter() = EnterGamePresenter()

  fun provideGameListPresenter(activity: GameListActivity) = GameListPresenter(provideGetGameListUseCase(), provideEnterGameListUseCase(), provideGameListRouter(activity))
  fun provideMenuPresenter(activity: MenuActivity) = MenuPresenter(provideMenuRouter(activity), provideCreateGameUseCase())
  fun providePlayerListAdmintPresenter(activity: PlayerListAdminActivity) = UserListPresenter(
      provideGetUserListUseCase(),
      provideExitGameUseCase(),
      provideStartGameUseCase(),
      provideUserListAdminRouter(activity))

  fun providePlayerListGuestPresenter(activity: PlayerListGuestActivity) = UserListPresenter(
      provideGetUserListUseCase(),
      provideExitGameUseCase(),
      provideStartGameUseCase(),
      provideUserListGuestRouter(activity))

  fun provideGamePresenter(activity: GameActivity) = GamePresenter(provideGameRepository(), provideLeaveGameUseCase(), provideGameRouter(activity))


  fun provideQuestionPresenter(activity: QuestionActivity) = QuestionPresenter(provideQuestionRepository(), provideFireGameRepository(), provideQuestionRouter(activity))
  fun provideWaitPresenter(activity: WaitActivity) = WaitPresenter(provideGetTurnUseCase(), provideLeaveGameUseCase(), provideGetUserListUseCase(), provideWaitRouter(activity))


}