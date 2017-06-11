package com.pedrodavidlp.ittrivial.login.presenter

import com.pedrodavidlp.ittrivial.login.contract.EnterGameContract
import com.pedrodavidlp.ittrivial.login.domain.usecase.SelectUsername

class EnterGamePresenter(private val select: SelectUsername) : EnterGameContract.InteractorOutput {

  lateinit private var vw: EnterGameContract.View
  fun pickUsername(username: String) {
    select.selectUsername(username, this)
  }

  fun setView(view: EnterGameContract.View) {
    vw = view
  }

  override fun usernameIsBlank() {
    vw.showError("Por favor, escriba un nombre de usuario")
  }

  override fun usernameContainsSymbols() {
    vw.showError("El nombre de usuario solo puede contener caracteres alfanumericos")
  }

  override fun usernameHasLessThanFiveCharacters() {
    vw.showError("El nombre de usuario debe tener mas de 4 caracteres")
  }

  override fun onUsernameSelected() {
    vw.usernameSelected()
  }

  override fun usernameHasSpacesBetweenWords() {
    vw.showError("El nombre de usuario no puede tener espacios en blanco")
  }
}
