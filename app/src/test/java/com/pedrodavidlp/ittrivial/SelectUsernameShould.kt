package com.pedrodavidlp.ittrivial

import com.pedrodavidlp.ittrivial.login.contract.EnterGameContract
import com.pedrodavidlp.ittrivial.login.domain.usecase.SelectUsername
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

class SelectUsernameShould {
  @Mock lateinit var callback: EnterGameContract.InteractorOutput
  private val selectUsername = SelectUsername()

  @Before
  fun setUp() {
    MockitoAnnotations.initMocks(this)
  }

  @Test
  fun `Reject username if is blank`() {
    selectUsername.selectUsername("     ", callback)
    verify(callback).usernameIsBlank()
  }

  @Test
  fun `Reject username if have less than 5 characters`() {
    selectUsername.selectUsername("hola", callback)
    verify(callback).usernameHasLessThanFiveCharacters()
  }

  @Test
  fun `Reject username if have not alphanumeric symbols`() {
    selectUsername.selectUsername("soy_el_4", callback)
    verify(callback).usernameContainsSymbols()
  }

  @Test
  fun `Reject username if have spaces between words`() {
    selectUsername.selectUsername("en europa soy un don", callback)
    verify(callback).usernameHasSpacesBetweenWords()
  }

  @Test
  fun `Accept username if all requirements are correct`() {
    selectUsername.selectUsername("nhemesy", callback)
    verify(callback).onUsernameSelected()
  }
}