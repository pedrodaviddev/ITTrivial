package com.pedrodavidlp.ittrivial.base.pattern

import com.pedrodavidlp.ittrivial.game.data.FireGameRepository
import com.pedrodavidlp.ittrivial.game.data.FireQuestionRepository
import com.pedrodavidlp.ittrivial.game.domain.repository.GameRepository
import com.pedrodavidlp.ittrivial.game.domain.repository.QuestionRepository
import com.pedrodavidlp.ittrivial.game.domain.usecase.GetPlayerList
import com.pedrodavidlp.ittrivial.game.domain.usecase.GetTurn
import com.pedrodavidlp.ittrivial.game.presenter.GamePresenter
import com.pedrodavidlp.ittrivial.game.presenter.QuestionPresenter
import com.pedrodavidlp.ittrivial.game.presenter.RoulettePresenter
import com.pedrodavidlp.ittrivial.game.presenter.WaitPresenter
import com.pedrodavidlp.ittrivial.game.router.GameRouter
import com.pedrodavidlp.ittrivial.game.view.activity.GameActivity
import com.pedrodavidlp.ittrivial.login.data.FireLobbyRepository
import com.pedrodavidlp.ittrivial.login.domain.usecase.*
import com.pedrodavidlp.ittrivial.login.presenter.*
import com.pedrodavidlp.ittrivial.login.router.EnterGameRouter
import com.pedrodavidlp.ittrivial.login.router.GameListRouter
import com.pedrodavidlp.ittrivial.login.router.MenuRouter
import com.pedrodavidlp.ittrivial.login.router.UserListRouter
import com.pedrodavidlp.ittrivial.login.view.*

object ServiceLocator {
  object Game {
    object Repository {
      internal fun provideGame(): GameRepository = FireGameRepository()
      internal fun provideQuestion(): QuestionRepository = FireQuestionRepository()
    }

    object Interactor {
      internal fun provideGetPlayerList() = GetPlayerList(Lobby.Repository.provideLobby())
      internal fun provideGetTurn() = GetTurn(Game.Repository.provideGame())
    }

    object Presenter {
      fun provideRoulette() =
          RoulettePresenter(Game.Repository.provideGame(),
              Game.Router.provideGame(GameActivity.instance))

      fun provideQuestion() =
          QuestionPresenter(Game.Repository.provideQuestion(),
              Game.Repository.provideGame(),
              Game.Router.provideGame(GameActivity.instance))

      fun provideWait() =
          WaitPresenter(Game.Interactor.provideGetTurn(),
              Game.Interactor.provideGetPlayerList(),
              Game.Router.provideGame(GameActivity.instance))

      fun provideGame(activity: GameActivity): GamePresenter =
          GamePresenter(Game.Router.provideGame(activity))
    }

    object Router {
      fun provideGame(activity: GameActivity) = GameRouter(activity)
    }
  }

  object Lobby {
    object Repository {
      internal fun provideLobby() = FireLobbyRepository()

    }

    object Interactor {
      internal fun provideGetGameList() = GetGameList(Repository.provideLobby())
      internal fun provideNotifyStartGame() = NotifyStartGame(Lobby.Repository.provideLobby())
      internal fun provideSelectUsername() = SelectUsername()
      internal fun provideEnterGameList() = EnterGame(Lobby.Repository.provideLobby())
      internal fun provideCreateGame() = CreateGame(Lobby.Repository.provideLobby())
      internal fun provideGetUserList() = GetUserList(Lobby.Repository.provideLobby())
      internal fun provideExitGame() = ExitGame(Lobby.Repository.provideLobby())
      internal fun provideStartGame() = StartGame(Lobby.Repository.provideLobby())
    }

    object Presenter {
      fun provideEnterGame(activity: EnterGameActivity) =
          EnterGamePresenter(Lobby.Interactor.provideSelectUsername(),
              Lobby.Router.provideEnterGame(activity))

      fun provideGameList(activity: GameListActivity) =
          GameListPresenter(Lobby.Interactor.provideGetGameList(),
              Lobby.Interactor.provideEnterGameList(),
              Lobby.Router.provideGameList(activity))

      fun provideMenu(activity: MenuActivity) =
          MenuPresenter(Lobby.Interactor.provideCreateGame(),
              Lobby.Router.provideMenu(activity))

      fun providePlayerListAdmin(activity: PlayerListAdminActivity) = UserListPresenter(
          Lobby.Interactor.provideGetUserList(),
          Lobby.Interactor.provideExitGame(),
          Lobby.Interactor.provideStartGame(),
          Lobby.Router.provideUserListAdmin(activity))

      fun providePlayerListGuest(activity: PlayerListGuestActivity) = UserListGuestPresenter(
          Lobby.Interactor.provideGetUserList(),
          Lobby.Interactor.provideExitGame(),
          Lobby.Interactor.provideStartGame(),
          Lobby.Interactor.provideNotifyStartGame(),
          Lobby.Router.provideUserListGuest(activity))
    }

    object Router {
      internal fun provideGameList(activity: GameListActivity) = GameListRouter(activity)
      internal fun provideEnterGame(activity: EnterGameActivity): EnterGameRouter = EnterGameRouter(activity)
      internal fun provideUserListAdmin(activity: PlayerListAdminActivity) = UserListRouter(activity)
      internal fun provideUserListGuest(activity: PlayerListGuestActivity) = UserListRouter(activity)
      internal fun provideMenu(activity: MenuActivity) = MenuRouter(activity)
    }
  }
}