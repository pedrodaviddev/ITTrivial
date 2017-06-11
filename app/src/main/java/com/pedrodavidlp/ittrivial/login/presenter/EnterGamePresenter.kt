package com.pedrodavidlp.ittrivial.login.presenter

import com.pedrodavidlp.ittrivial.login.contract.EnterGameContract
import com.pedrodavidlp.ittrivial.login.domain.usecase.SelectUsername

class EnterGamePresenter(private val select: SelectUsername) : EnterGameContract.InteractorOutput {
  fun pickUsername(username: String) {
    select.selectUsername(username)
  }

  override fun usernameIsBlank() {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  override fun usernameContainsSymbols() {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  override fun usernameHasLessThanFiveCharacters() {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  override fun onUsernameSelected() {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }
}