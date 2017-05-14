package com.pedrodavidlp.ittrivial.game.view

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.pedrodavidlp.ittrivial.R
import com.pedrodavidlp.ittrivial.game.contract.GameContract
import com.pedrodavidlp.ittrivial.game.data.MockGameRepository
import com.pedrodavidlp.ittrivial.game.domain.model.Player
import com.pedrodavidlp.ittrivial.game.presenter.GamePresenter
import com.pedrodavidlp.ittrivial.game.router.GameRouter
import com.pedrodavidlp.ittrivial.login.view.ScoreListAdapter
import kotlinx.android.synthetic.main.activity_match.*

class GameActivity : AppCompatActivity(), GameContract.View, Roulette.OnCategorySelected {

  lateinit var presenter: GamePresenter
  lateinit var router: GameRouter
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_match)
    router = GameRouter(this)
    presenter = GamePresenter(MockGameRepository())
    presenter.setView(this)
    presenter.init()
  }

  override fun loadList(playerList: List<Player>) {
    (gamePlayerList.adapter as ScoreListAdapter).setList(playerList)
  }

  override fun initUi() {
    gamePlayerList.adapter = ScoreListAdapter()
    gamePlayerList.layoutManager = LinearLayoutManager(applicationContext)
    roulette.setWheelChangeListener(object : Roulette.RouletteChangeListener {
      override fun onSelectionChange(selectedPosition: Category) {
        selected_position_text.text = selectedPosition.name
      }
    })
    roulette.setOnCategorySelectedListener(this)
  }

  override fun finishedGame() {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  override fun onCategorySelected(category: Category) {
    presenter.makeTransitionTo(category)
  }
}
