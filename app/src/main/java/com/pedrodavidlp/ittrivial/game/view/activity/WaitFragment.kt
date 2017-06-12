package com.pedrodavidlp.ittrivial.game.view.activity


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.pedrodavidlp.ittrivial.R
import com.pedrodavidlp.ittrivial.ServiceLocator
import com.pedrodavidlp.ittrivial.game.contract.WaitContract
import com.pedrodavidlp.ittrivial.game.domain.model.Player
import com.pedrodavidlp.ittrivial.game.presenter.WaitPresenter
import com.pedrodavidlp.ittrivial.game.router.WaitRouter
import com.pedrodavidlp.ittrivial.login.view.PlayerListAdapter
import kotlinx.android.synthetic.main.fragment_wait.*

class WaitFragment : Fragment(), WaitContract.View {

  lateinit var presenter: WaitPresenter
  lateinit var router: WaitRouter

  override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
    presenter = ServiceLocator.provideWaitPresenter()
    return inflater?.inflate(R.layout.fragment_wait, container, false)
  }

  override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
    playerList.adapter = PlayerListAdapter()
    playerList.layoutManager = LinearLayoutManager(context)
    super.onViewCreated(view, savedInstanceState)
  }

  override fun changeTurn(player: Player) {
    playerTurnIndicator.text = "Turno de @${player.username}"
  }

  override fun myTurn() {
    waitYourTurnText.text = "ES TU TURNO!!!!"
  }

  override fun showListPlayers(listPlayer: List<Player>) {
    (playerList.adapter as PlayerListAdapter).listPlayers = listPlayer
  }
}

