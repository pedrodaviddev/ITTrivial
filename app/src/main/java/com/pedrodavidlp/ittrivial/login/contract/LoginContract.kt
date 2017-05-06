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

    interface Interactor {
        fun login(username: String)
    }

    interface InteractorOutput {
        fun onLoginSuccess()
        fun onLoginError()
    }

    interface Router {
        fun goToMenu(username: String)
    }
}