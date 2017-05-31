package com.pedrodavidlp.ittrivial.game.view.activity


import com.pedrodavidlp.ittrivial.game.contract.WaitContract
import com.pedrodavidlp.ittrivial.game.data.MockGameRepository
import com.pedrodavidlp.ittrivial.game.presenter.WaitPresenter
import com.pedrodavidlp.ittrivial.game.router.WaitRouter
import kotlinx.android.synthetic.main.activity_wait.*

class WaitActivity : android.support.v7.app.AppCompatActivity(), WaitContract.View {
  lateinit var presenter: WaitPresenter

  lateinit var router: WaitRouter
  override fun onCreate(savedInstanceState: android.os.Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(com.pedrodavidlp.ittrivial.R.layout.activity_wait)
    router = WaitRouter(this)
    presenter = WaitPresenter(com.pedrodavidlp.ittrivial.game.domain.usecase.GetTurn(MockGameRepository()))
    presenter.setView(this)
    presenter.init()
  }

  override fun changeTurn(player: com.pedrodavidlp.ittrivial.game.domain.model.Player) {
    playerTurnIndicator.text = "Turno de @${player.username}"
  }

  override fun myTurn() {
    waitYourTurnText.text = "ES TU TURNO!!!!"
    router.goToGame()
  }
}
