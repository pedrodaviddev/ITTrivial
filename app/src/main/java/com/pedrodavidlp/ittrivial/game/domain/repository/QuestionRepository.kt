package com.pedrodavidlp.ittrivial.game.domain.repository

import com.pedrodavidlp.ittrivial.game.contract.QuestionContract

interface QuestionRepository {
  fun loadQuestion(callback: QuestionContract.InteractorOutput)
}