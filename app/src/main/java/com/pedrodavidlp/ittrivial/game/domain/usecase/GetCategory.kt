package com.pedrodavidlp.ittrivial.game.domain.usecase

import com.pedrodavidlp.ittrivial.base.Observer
import com.pedrodavidlp.ittrivial.game.contract.WaitContract
import com.pedrodavidlp.ittrivial.game.domain.model.Game
import kotlin.properties.Delegates

class GetCategory(): WaitContract.Interactor {
  override fun getCategory(game: Game, callback: WaitContract.InteractorOutput) {
    var category: String by Delegates.observable("") {_, _, new ->
      observers.forEach {it.onChange(new)}
    }   }

  override fun getTurn(game: Game, callback: WaitContract.InteractorOutput) {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }


  private val observers = mutableListOf<Observer<String>>()

  fun addObserver(observer: Observer<String>){
    observers.add(observer)
  }

  fun removeObserver(observer: Observer<String>){
    observers.remove(observer)
  }

}