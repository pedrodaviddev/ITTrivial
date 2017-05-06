package com.pedrodavidlp.ittrivial.login.contract

class MenuContract {
  interface View {
    fun showError(message: String)
  }

  interface Presenter {
    fun init()
    fun setView(view: View)

  }

  interface Router {
    fun goToPlayerList()
    fun goToGame()
  }
}