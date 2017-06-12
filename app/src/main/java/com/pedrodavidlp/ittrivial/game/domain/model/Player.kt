package com.pedrodavidlp.ittrivial.game.domain.model

import com.pedrodavidlp.ittrivial.game.view.Category

data class Player(var username: String = "Error",
                  val admin: Boolean = false,
                  var history: Boolean = false,
                  var hardware: Boolean = false,
                  var network: Boolean = false,
                  var software: Boolean = false,
                  var enterprise: Boolean = false) {
  fun winCategory(category: Category) {
    when (category) {
      Category.HISTORY -> history = true
      Category.SOFTWARE -> software = true
      Category.NETWORK -> network = true
      Category.ENTERPRISE -> enterprise = true
      Category.HARDWARE -> hardware = true
    }
  }

  fun isWinner(): Boolean {
    return history && hardware && enterprise && software && network
  }
}