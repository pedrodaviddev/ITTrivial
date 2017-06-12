package com.pedrodavidlp.ittrivial.game.view.activity.transition

import android.view.animation.Animation
import kotlinx.android.synthetic.main.activity_match.*

class HistoryStrategy(val activity: com.pedrodavidlp.ittrivial.game.view.activity.RouletteFragment) {
  fun transitionToQuestion() {
    activity.roulette.alpha = 0.4f
    activity.gamePlayerList.alpha = 0.4f
    // fragment.categoryImage.setImageResource(R.drawable.ic_paint_spot)
    //fragment.categoryImage.visibility = VISIBLE
    val animation = android.view.animation.RotateAnimation(0.0f, 360.0f
        , Animation.RELATIVE_TO_SELF, 0f
        , Animation.RELATIVE_TO_SELF, 0f)
    animation.duration = 1000
    // fragment.categoryImage.startAnimation(animation)
  }

}