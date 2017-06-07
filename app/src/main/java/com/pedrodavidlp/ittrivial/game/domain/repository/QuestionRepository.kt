package com.pedrodavidlp.ittrivial.game.domain.repository

import com.pedrodavidlp.ittrivial.game.contract.QuestionContract
import com.pedrodavidlp.ittrivial.game.domain.model.Game
import com.pedrodavidlp.ittrivial.game.domain.model.Player
import com.pedrodavidlp.ittrivial.game.view.Category

interface QuestionRepository {
  fun getQuestion(category : Category, callback: QuestionContract.InteractorOutput) //To change: make category a Category object and not a String.
  fun updateCrowns(game : Game, category: Category, player: Player) //Same
}