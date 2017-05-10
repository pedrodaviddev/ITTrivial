package com.pedrodavidlp.ittrivial.login.view

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.pedrodavidlp.ittrivial.R
import com.pedrodavidlp.ittrivial.game.domain.model.Game
import com.pedrodavidlp.ittrivial.login.contract.GameListContract
import com.pedrodavidlp.ittrivial.login.data.MockLobbyRepository
import com.pedrodavidlp.ittrivial.login.domain.usecase.GetGames
import com.pedrodavidlp.ittrivial.login.router.GameListPresenter
import com.pedrodavidlp.ittrivial.login.view.PlayerListGuestActivity
import kotlinx.android.synthetic.main.activity_games_list.*
import org.jetbrains.anko.startActivity

class GamesListActivity : AppCompatActivity(), GameListContract.View {
  lateinit private var presenter: GameListPresenter

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_games_list)
    gamesList.adapter = GamesListAdapter()
    gamesList.layoutManager = LinearLayoutManager(applicationContext)
    gamesList.setOnClickListener {
      startActivity<PlayerListGuestActivity>()
    }
    presenter = GameListPresenter(GetGames(MockLobbyRepository()))
    presenter.setView(this)
    presenter.init()
  }

  override fun onLoadList(gameList: List<Game>) {
    (gamesList.adapter as GamesListAdapter).setList(gameList)
  }

  override fun showError(message: String) {

  }
}
