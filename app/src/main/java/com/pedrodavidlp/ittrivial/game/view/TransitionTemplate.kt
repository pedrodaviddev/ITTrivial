package com.pedrodavidlp.ittrivial.game.view

abstract class TransitionTemplate {

  protected fun makeTransition(){
    animate()
    setImage()
    goToQuestion()
  }

  abstract fun goToQuestion()

  abstract fun setImage()

  abstract fun animate()
}