package com.pedrodavidlp.ittrivial.game.view.activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.View.VISIBLE
import com.pedrodavidlp.ittrivial.R
import com.pedrodavidlp.ittrivial.ServiceLocator
import com.pedrodavidlp.ittrivial.game.contract.GameContract
import com.pedrodavidlp.ittrivial.game.domain.model.Player
import com.pedrodavidlp.ittrivial.game.presenter.GamePresenter
import com.pedrodavidlp.ittrivial.game.router.GameRouter
import com.pedrodavidlp.ittrivial.game.view.Category
import com.pedrodavidlp.ittrivial.game.view.Roulette
import com.pedrodavidlp.ittrivial.login.view.PlayerListAdapter
import kotlinx.android.synthetic.main.activity_match.*
import org.jetbrains.anko.alert

class GameActivity : AppCompatActivity(), GameContract.View, Roulette.OnCategorySelected {

  lateinit var presenter: GamePresenter
  lateinit var router: GameRouter
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_match)
    presenter = ServiceLocator.provideGamePresenter(this)
    presenter.setView(this)
    presenter.manageTurn(intent.getBooleanExtra("a", false))
    presenter.init()
  }

  override fun loadList(playerList: List<Player>) {
    (gamePlayerList.adapter as PlayerListAdapter).listPlayers = playerList
  }

  override fun initUi() {
    gamePlayerList.adapter = PlayerListAdapter()
    gamePlayerList.layoutManager = LinearLayoutManager(applicationContext)
    roulette.setWheelChangeListener(object : Roulette.RouletteChangeListener {
      override fun onSelectionChange(category: Category) {
        selectedCategoryText.visibility = VISIBLE
        selectedCategoryText.text = category.name
      }
    })
    roulette.setOnCategorySelectedListener(this)
  }

  override fun finishedGame() {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  override fun onCategorySelected(category: Category) {
    presenter.goToQuestion(category, transitionImage)
  }

  override fun onBackPressed() {
    alert("Are you sure to leave the game?"){
      title("Exit")
      yesButton {
        //TODO
      }
      noButton {}
    }.show()
  }

  override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
    presenter.manageBack(resultCode)
  }
}
