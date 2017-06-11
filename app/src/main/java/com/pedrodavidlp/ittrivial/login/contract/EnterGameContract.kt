package com.pedrodavidlp.ittrivial.login.contract

class EnterGameContract {
  interface View {
    fun showError(message: String)
    fun usernameSelected()
  }

  interface Presenter {
    fun init()
    fun setView(view: View)
    fun onLogin(username: String)
  }

  interface InteractorOutput {
    fun usernameIsBlank()
    fun usernameContainsSymbols()
    fun usernameHasLessThanFiveCharacters()
    fun onUsernameSelected()
    fun usernameHasSpacesBetweenWords()
  }

  interface Router {
    fun goToMenu(username: String)
  }
}