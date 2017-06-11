package com.pedrodavidlp.ittrivial.game.domain.model

data class Player(var username: String = "Error",
                  val admin: Boolean,
                  var history: Boolean = false,
                  var hardware: Boolean = false,
                  var network: Boolean = false,
                  var software: Boolean = false,
                  var enterprise: Boolean = false
)