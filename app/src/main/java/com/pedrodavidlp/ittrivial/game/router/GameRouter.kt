package com.pedrodavidlp.ittrivial.game.router

import android.os.Handler
import com.pedrodavidlp.ittrivial.game.contract.GameContract
import com.pedrodavidlp.ittrivial.game.view.Category
import com.pedrodavidlp.ittrivial.game.view.activity.GameActivity
import com.pedrodavidlp.ittrivial.game.view.activity.QuestionActivity
import org.jetbrains.anko.startActivity

class GameRouter(val activity: GameActivity) : GameContract.Router {

  override fun goToQuestion(category: Category){
    Handler().postDelayed({
      activity.startActivity<QuestionActivity>(Pair("Category", category))
    }, 1000)
  }

  override fun goToMenu() {
    activity.finish()
  }
}