package com.pedrodavidlp.ittrivial.login.contract

class GameListContract {
  interface View {
    fun showError(message: String)
  }

  interface Presenter {
    fun init()
    fun setView(view: View)
    fun onFetchGameList()
  }

  interface Interactor {
    fun getGameList()
  }

  interface InteractorOutput {
    fun onFetchGameListSuccess()
    fun onFetchGameListError()
  }

  interface Router {
    fun goToPlayerGameList()
  }
}