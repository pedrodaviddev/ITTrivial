package com.pedrodavidlp.ittrivial.login.contract

class PlayerListAdminContract {
  interface View {
    fun showError()
  }

  interface presenter {
    fun onStartGame()
  }

  interface interactor {
    fun startGame()
  }

  interface InteractorOutput {
    fun onStartGameSuccess()
    fun onStartGameError()
  }

  interface Router {
    fun goToGame()
  }
}