package com.pedrodavidlp.ittrivial.login.contract

class EnterGameContract {
  interface View {
    fun showError(message: String)
  }

  interface Presenter {
    fun init()
    fun setView(view: View)
    fun onLogin(username: String)
  }

  interface InteractorOutput {
    fun onLoginSuccess()
    fun onLoginError()
    fun onEnterGame()
  }

  interface Router {
    fun goToMenu(username: String)
  }
}