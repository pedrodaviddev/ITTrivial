package com.pedrodavidlp.ittrivial.game.domain.repository

import com.pedrodavidlp.ittrivial.game.contract.QuestionContract
import com.pedrodavidlp.ittrivial.game.domain.model.Game
import com.pedrodavidlp.ittrivial.game.domain.model.Player

interface QuestionRepository {
  fun getQuestion(category : String, callback: QuestionContract.InteractorOutput) //To change: make category a Category object and not a String.
  fun updateCrowns(game : Game, category: String, player: Player) //Same
}