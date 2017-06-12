package com.pedrodavidlp.ittrivial.game.view.activity

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.View.VISIBLE
import android.view.ViewGroup
import com.pedrodavidlp.ittrivial.R
import com.pedrodavidlp.ittrivial.ServiceLocator
import com.pedrodavidlp.ittrivial.game.contract.RouletteContract
import com.pedrodavidlp.ittrivial.game.domain.model.Player
import com.pedrodavidlp.ittrivial.game.presenter.RoulettePresenter
import com.pedrodavidlp.ittrivial.game.view.Category
import com.pedrodavidlp.ittrivial.game.view.Roulette
import com.pedrodavidlp.ittrivial.login.view.PlayerListAdapter
import kotlinx.android.synthetic.main.fragment_roulette.*

class RouletteFragment : Fragment(), RouletteContract.View, Roulette.OnCategorySelected {

  lateinit var presenter: RoulettePresenter

  override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
    presenter = ServiceLocator.provideRoulettePresenter(this)
    return inflater?.inflate(R.layout.fragment_roulette, container, false)
  }

  override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    activity.title = "¡Toca para girar la ruleta!"
    presenter.setView(this)
    presenter.init()
  }

  override fun loadList(playerList: List<Player>) {
    (gamePlayerList.adapter as PlayerListAdapter).listPlayers = playerList
  }

  override fun initUi() {
    gamePlayerList.adapter = PlayerListAdapter()
    gamePlayerList.layoutManager = LinearLayoutManager(context)
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

}
