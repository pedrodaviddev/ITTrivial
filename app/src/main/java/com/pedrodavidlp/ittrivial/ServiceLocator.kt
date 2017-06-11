package com.pedrodavidlp.ittrivial

import android.app.Activity
import android.support.v7.app.AppCompatActivity
import com.pedrodavidlp.ittrivial.game.data.FireGameRepository
import com.pedrodavidlp.ittrivial.game.data.FireQuestionRepository
import com.pedrodavidlp.ittrivial.game.data.MockGameRepository
import com.pedrodavidlp.ittrivial.game.domain.usecase.GetTurn
import com.pedrodavidlp.ittrivial.game.presenter.GamePresenter
import com.pedrodavidlp.ittrivial.game.presenter.QuestionPresenter
import com.pedrodavidlp.ittrivial.game.presenter.WaitPresenter
import com.pedrodavidlp.ittrivial.game.router.GameRouter
import com.pedrodavidlp.ittrivial.game.router.QuestionRouter
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

  private val repository = FireLobbyRepository()

  // *LOGIN*


  //EnterGame
  fun provideEnterGamePresenter() = EnterGamePresenter()

  // GameList
  fun provideGameListPresenter(activity: GameListActivity) = GameListPresenter(provideGetGameListUseCase(), provideEnterGameListUseCase(), provideGameListRouter(activity))

  private fun provideGetGameListUseCase() = GetGameList(repository)
  private fun provideEnterGameListUseCase() = EnterGame(repository)
  private fun provideGameListRouter(activity: GameListActivity) = GameListRouter(activity)

  //Menu
  fun provideMenuPresenter(activity: MenuActivity) = MenuPresenter(provideMenuRouter(activity), provideCreateGameUseCase())

  private fun provideCreateGameUseCase() = CreateGame(repository)
  private fun provideMenuRouter(activity: MenuActivity) = MenuRouter(activity)

  //PlayerList Admin & Guest

  fun providePlayerListAdmintPresenter(activity: PlayerListAdminActivity) =  UserListPresenter(
      provideGetUserListUseCase(),
      provideExitGameUseCase(),
      provideStartGameUseCase(),
      provideUserListAdminRouter(activity))

  fun providePlayerListGuestPresenter(activity: PlayerListGuestActivity) = UserListPresenter(
      provideGetUserListUseCase(),
      provideExitGameUseCase(),
      provideStartGameUseCase(),
      provideUserListGuestRouter(activity))

  private fun provideGetUserListUseCase() = GetUserList(repository)
  private fun provideExitGameUseCase() = ExitGame(repository)
  private fun provideStartGameUseCase() = StartGame(repository)
  private fun provideUserListAdminRouter(activity: PlayerListAdminActivity) = UserListRouter(activity)
  private fun provideUserListGuestRouter(activity: PlayerListGuestActivity) = UserListRouter(activity)


  //*GAME*

  //Game
  fun provideGamePresenter(activity: GameActivity) = GamePresenter(provideGameRepository(), provideGameRouter(activity))

  private fun provideGameRouter(activity: GameActivity) = GameRouter(activity)
  private fun provideGameRepository() = MockGameRepository()

  //Question

  fun provideQuestionPresenter(activity: QuestionActivity) = QuestionPresenter(provideQuestionRepository(), provideFireGameRepository(), provideQuestionRouter(activity))
  private fun provideQuestionRepository() = FireQuestionRepository()
  private fun provideFireGameRepository() = FireGameRepository()
  private fun provideQuestionRouter(activity: QuestionActivity) = QuestionRouter(activity)

  //Wait

  fun provideWaitPresenter() = WaitPresenter(provideGetTurnUseCase())

  private fun provideGetTurnUseCase() = GetTurn(provideFireGameRepository())
}