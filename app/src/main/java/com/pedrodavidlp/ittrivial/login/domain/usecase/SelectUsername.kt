package com.pedrodavidlp.ittrivial.login.domain.usecase

import com.pedrodavidlp.ittrivial.base.domain.data.Session
import com.pedrodavidlp.ittrivial.login.contract.EnterGameContract
import java.util.regex.Pattern

class SelectUsername {
  fun selectUsername(username: String, callback: EnterGameContract.InteractorOutput) {
    if (isBlank(username)) {
      callback.usernameIsBlank()
    } else if (haveLessThanFiveCharacters(username)) {
      callback.usernameHasLessThanFiveCharacters()
    } else if (haveSpacesBetweenWords(username)) {
      callback.usernameHasSpacesBetweenWords()
    } else if (haveNotAllowedSymbols(username)) {
      callback.usernameContainsSymbols()
    } else {
      Session.username = username
      callback.onUsernameSelected()
    }
  }

  private fun haveSpacesBetweenWords(username: String): Boolean {
    return username.contains(" ")
  }

  private fun haveNotAllowedSymbols(username: String): Boolean {
    val pattern = Pattern.compile("[^A-Za-z0-9]")
    val match = pattern.matcher(username)
    return match.find() && username.isNotEmpty()
  }

  private fun isBlank(username: String): Boolean {
    return username.trim().isEmpty()
  }

  private fun haveLessThanFiveCharacters(username: String): Boolean {
    return username.length < 5
  }
}