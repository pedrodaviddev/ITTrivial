package com.pedrodavidlp.ittrivial.game.view

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.pedrodavidlp.ittrivial.R
import com.pedrodavidlp.ittrivial.login.view.ScoreListAdapter
import kotlinx.android.synthetic.main.activity_match.*

class GameActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_match)

    roulette.setWheelChangeListener(object : Roulette.RouletteChangeListener {
      override fun onSelectionChange(selectedPosition: String) {
        selected_position_text.text = selectedPosition
      }
    })
    gamePlayerList.adapter = ScoreListAdapter()
    gamePlayerList.layoutManager = LinearLayoutManager(applicationContext)
  }
}
