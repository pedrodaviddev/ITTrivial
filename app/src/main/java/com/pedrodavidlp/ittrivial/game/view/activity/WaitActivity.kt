package com.pedrodavidlp.ittrivial.game.view.activity


import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.pedrodavidlp.ittrivial.R
import com.pedrodavidlp.ittrivial.game.contract.WaitContract
import com.pedrodavidlp.ittrivial.game.data.FireGameRepository
import com.pedrodavidlp.ittrivial.game.domain.model.Player
import com.pedrodavidlp.ittrivial.game.domain.usecase.GetTurn
import com.pedrodavidlp.ittrivial.game.presenter.WaitPresenter
import com.pedrodavidlp.ittrivial.game.router.WaitRouter
import com.pedrodavidlp.ittrivial.login.data.FireLobbyRepository
import com.pedrodavidlp.ittrivial.login.domain.usecase.GetUserList
import com.pedrodavidlp.ittrivial.login.view.PlayerListAdapter
import kotlinx.android.synthetic.main.activity_wait.*

class WaitActivity : AppCompatActivity(), WaitContract.View {

  lateinit var presenter: WaitPresenter
  lateinit var router: WaitRouter

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_wait)
    this.title = "Lista de jugadores"
    playerList.adapter = PlayerListAdapter()
    playerList.layoutManager = LinearLayoutManager(applicationContext)
    router = WaitRouter(this)
    presenter = WaitPresenter(GetTurn(FireGameRepository()), GetUserList(FireLobbyRepository()))
    presenter.setView(this)
    presenter.init()
  }

  override fun changeTurn(player: Player) {
    playerTurnIndicator.text = "Turno de @${player.username}"
  }

  override fun myTurn() {
    waitYourTurnText.text = "ES TU TURNO!!!!"
    router.goToGame()
  }

  override fun showListPlayers(listPlayer: List<Player>) {
    (playerList.adapter as PlayerListAdapter).listPlayers = listPlayer
  }
}

