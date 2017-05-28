package com.pedrodavidlp.ittrivial.login.view

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import com.pedrodavidlp.ittrivial.R
import com.pedrodavidlp.ittrivial.game.domain.model.Game
import com.pedrodavidlp.ittrivial.login.contract.GameListContract
import com.pedrodavidlp.ittrivial.login.data.FireLobbyRepository
import com.pedrodavidlp.ittrivial.login.domain.usecase.EnterGame
import com.pedrodavidlp.ittrivial.login.domain.usecase.GetGameList
import com.pedrodavidlp.ittrivial.login.presenter.GameListPresenter
import com.pedrodavidlp.ittrivial.login.router.GameListRouter
import kotlinx.android.synthetic.main.activity_games_list.*
import org.jetbrains.anko.startActivity

class GameListActivity : AppCompatActivity(), GameListContract.View {
  lateinit private var presenter: GameListPresenter

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_games_list)
    this.title = "Lista de partidas"
    gamesList.adapter = GamesListAdapter()
    gamesList.layoutManager = GridLayoutManager(applicationContext, 2)

    RecyclerListener.addTo(gamesList).setOnItemClickListener(object : RecyclerListener.OnItemClickListener {
      override fun onItemClicked(recyclerView: RecyclerView, position: Int, v: View) {
        presenter.enterGame(Game(((recyclerView.adapter) as GamesListAdapter).listGames[position].name))
      }
    })
    presenter = GameListPresenter(GetGameList(FireLobbyRepository()), EnterGame(FireLobbyRepository()), GameListRouter(this))
    presenter.setView(this)
    presenter.init()
  }

  override fun onLoadList(gameList: List<Game>) {
    (gamesList.adapter as GamesListAdapter).listGames = gameList
  }

  override fun showError(message: String) {

  }
}
