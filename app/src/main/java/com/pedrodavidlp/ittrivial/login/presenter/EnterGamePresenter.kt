package com.pedrodavidlp.ittrivial.login.presenter

import com.pedrodavidlp.ittrivial.base.domain.data.Session

class EnterGamePresenter {
  fun pickUsername(username: String){
    Session.username = username
  }
}