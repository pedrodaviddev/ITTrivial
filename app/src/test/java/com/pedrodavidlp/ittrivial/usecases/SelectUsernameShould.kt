package com.pedrodavidlp.ittrivial.usecases

import org.mockito.Mockito.verify

class SelectUsernameShould {
  @org.mockito.Mock lateinit var callback: com.pedrodavidlp.ittrivial.login.contract.EnterGameContract.InteractorOutput
  private val selectUsername = com.pedrodavidlp.ittrivial.login.domain.usecase.SelectUsername()

  @org.junit.Before
  fun setUp() {
    org.mockito.MockitoAnnotations.initMocks(this)
  }

  @org.junit.Test
  fun `Reject username if is blank`() {
    selectUsername.selectUsername("     ", callback)
    verify(callback).usernameIsBlank()
  }

  @org.junit.Test
  fun `Reject username if have less than 5 characters`() {
    selectUsername.selectUsername("hola", callback)
    verify(callback).usernameHasLessThanFiveCharacters()
  }

  @org.junit.Test
  fun `Reject username if have not alphanumeric symbols`() {
    selectUsername.selectUsername("soy_el_4", callback)
    verify(callback).usernameContainsSymbols()
  }

  @org.junit.Test
  fun `Reject username if have spaces between words`() {
    selectUsername.selectUsername("en europa soy un don", callback)
    verify(callback).usernameHasSpacesBetweenWords()
  }

  @org.junit.Test
  fun `Accept username if all requirements are correct`() {
    selectUsername.selectUsername("nhemesy", callback)
    verify(callback).onUsernameSelected()
  }
}