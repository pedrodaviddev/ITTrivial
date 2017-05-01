package com.pedrodavidlp.ittrivial

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.TextView

class MatchActivity : AppCompatActivity() {
  lateinit private var wheelMenu: Roulette
  lateinit private var selectedPositionText: TextView

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_match)

    wheelMenu = findViewById(R.id.wheelMenu) as Roulette
    selectedPositionText = findViewById(R.id.selected_position_text) as TextView

    wheelMenu.setWheelChangeListener(object : Roulette.RouletteChangeListener {
      override fun onSelectionChange(selectedPosition: String) {
        selectedPositionText.text = selectedPosition
      }
    })
  }
}
