package com.pedrodavidlp.ittrivial

import android.view.animation.Interpolator


class RouletteInterpolator : Interpolator {
  override fun getInterpolation(ratio: Float): Float {
    if(ratio < 0.1) {
      return ratio * 10 * 0.4f
    } else if (ratio < 0.3){
      return 0.4f + ratio * 0.3f * 5f
    } else if(ratio < 0.6){
      return 0.7f + ratio * 0.2f * (10/3).toFloat()
    } else {
      return 0.9f + ratio * 0.1f * 2.5f
    }
  }

}
