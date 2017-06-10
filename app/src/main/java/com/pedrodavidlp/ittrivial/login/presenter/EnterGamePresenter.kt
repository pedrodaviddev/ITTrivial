package com.pedrodavidlp.ittrivial.login.presenter

import com.pedrodavidlp.ittrivial.base.domain.data.Session
import java.util.regex.Matcher
import java.util.regex.Pattern

class EnterGamePresenter {
  fun pickUsername(username: String){
    val pattern = Pattern.compile("[^A-Za-z0-9]")
    val match = pattern.matcher(username)
    val ok = match.find() && username.isNotEmpty()
    if(ok)
      Session.username = username
    else usernameError()
  }

  fun usernameError(){
    //TO-DO
  }
}