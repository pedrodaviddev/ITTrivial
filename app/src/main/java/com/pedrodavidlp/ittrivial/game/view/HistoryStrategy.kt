package com.pedrodavidlp.ittrivial.game.view

import android.view.View.VISIBLE
import android.view.animation.Animation
import android.view.animation.RotateAnimation
import com.pedrodavidlp.ittrivial.R
import kotlinx.android.synthetic.main.activity_match.*

class HistoryStrategy(val activity: GameActivity){
  fun transitionToQuestion(){
    activity.roulette.alpha = 0.4f
    activity.gamePlayerList.alpha = 0.4f
   // activity.categoryImage.setImageResource(R.drawable.ic_paint_spot)
    //activity.categoryImage.visibility = VISIBLE
    val animation = RotateAnimation(0.0f, 360.0f
        ,Animation.RELATIVE_TO_SELF,0f
        ,Animation.RELATIVE_TO_SELF,0f)
    animation.duration = 1000
   // activity.categoryImage.startAnimation(animation)
  }

}