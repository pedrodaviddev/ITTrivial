package com.pedrodavidlp.ittrivial.game.domain.repository

import com.pedrodavidlp.ittrivial.game.contract.QuestionContract
import com.pedrodavidlp.ittrivial.game.domain.model.Player

interface QuestionRepository {
  fun getQuestion(callback: QuestionContract.InteractorOutput)
  fun updateScore(player: Player)
}