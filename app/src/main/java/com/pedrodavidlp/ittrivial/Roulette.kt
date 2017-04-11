package com.pedrodavidlp.ittrivial

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.Animation
import android.view.animation.DecelerateInterpolator
import android.view.animation.RotateAnimation
import android.widget.ImageView
import android.widget.Toast
import java.util.*

class Roulette(private val rContext: Context, attrs: AttributeSet) : ImageView(rContext, attrs) {

  lateinit private var imageOriginal: Bitmap
  lateinit private var imageScaled: Bitmap     //variables for original and re-sized image
  private var rMatrix: Matrix = Matrix()     //Matrix used to perform rotations
  private var rHeight: Int = 0
  private var rWidth: Int = 0      //height and width of the view
  private var rTop: Int = 0      //the current rTop of the wheel (calculated in
  // wheel divs)
  private var totalRotation: Double = 0.toDouble()                  //variable that counts the total rotation
  // during a given rotation of the wheel by the
  // user (from ACTION_DOWN to ACTION_UP)
  private var divCount: Int = 0                          //no of divisions in the wheel
  private var divAngle: Int = 0                          //angle of each division
  private var selectedPosition: Int = 0                  //the section currently selected by the user.
  private var snapToCenterFlag = true       //variable that determines whether to snap the
  // wheel to the center of a div or not
  lateinit private var rChangeListener: RouletteChangeListener

  init {
    scaleType = ScaleType.MATRIX
    this.setOnTouchListener(RouletteTouchListener())

  }

  /**
   * Add a new listener to observe user selection changes.

   * @param rouletteChangeListener
   */
  fun setWheelChangeListener(rouletteChangeListener: RouletteChangeListener) {
    this.rChangeListener = rouletteChangeListener
  }

  /**
   * Returns the position currently selected by the user.

   * @return the currently selected position between 1 and divCount.
   */
  fun getSelectedPosition(): Int {
    return selectedPosition
  }

  /**
   * Set no of divisions in the wheel menu.

   * @param divCount no of divisions.
   */
  fun setDivCount(divCount: Int) {
    this.divCount = divCount

    divAngle = 360 / divCount
    totalRotation = (-1 * (divAngle / 2)).toDouble()
  }

  /**
   * Set the snap to center flag. If true, wheel will always snap to center of current section.

   * @param snapToCenterFlag
   */
  fun setSnapToCenterFlag(snapToCenterFlag: Boolean) {
    this.snapToCenterFlag = snapToCenterFlag
  }

  /**
   * Set a different rTop position. Default rTop position is 0.
   * Should be set after {#setDivCount(int) setDivCount} method and the value should be greater
   * than 0 and lesser
   * than divCount, otherwise the provided value will be ignored.

   * @param newTopDiv
   */
  fun setAlternateTopDiv(newTopDiv: Int) {

    if (newTopDiv < 0 || newTopDiv >= divCount)
      return
    else
      rTop = newTopDiv

    selectedPosition = rTop
  }

  /**
   * Set the wheel image.

   * @param drawableId the id of the drawable to be used as the wheel image.
   */
  fun setWheelImage(drawableId: Int) {
    setImageResource(drawableId)

  }

  /**
   * get the angle of a touch event.
   */
  private fun getAngle(x: Double, y: Double): Double {
    var x = x
    var y = y
    x -= rWidth / 2.0
    y = rHeight.toDouble() - y - rHeight / 2.0

    when (getQuadrant(x, y)) {
      1 -> return Math.asin(y / Math.hypot(x, y)) * 180 / Math.PI
      2 -> return 180 - Math.asin(y / Math.hypot(x, y)) * 180 / Math.PI
      3 -> return 180 + -1.0 * Math.asin(y / Math.hypot(x, y)) * 180.0 / Math.PI
      4 -> return 360 + Math.asin(y / Math.hypot(x, y)) * 180 / Math.PI
      else -> return 0.0
    }
  }

  /**
   * get the quadrant of the wheel which contains the touch point (x,y)

   * @return quadrant 1,2,3 or 4
   */
  private fun getQuadrant(x: Double, y: Double): Int {
    if (x >= 0) {
      return if (y >= 0) 1 else 4
    } else {
      return if (y >= 0) 2 else 3
    }
  }

  /**
   * rotate the wheel by the given angle

   * @param degrees
   */
  private fun rotateWheel(degrees: Float) {
    val animation: Animation = RotateAnimation(0.0f, degrees,
        Animation.RELATIVE_TO_SELF,0.5f,
        Animation.RELATIVE_TO_SELF,0.5f)
    animation.repeatCount = 0
    animation.interpolator = RouletteInterpolator()
    animation.duration = 3000
    animation.fillAfter = true
    super.startAnimation(animation)
    rotation += degrees

  }

  /**
   * Interface to to observe user selection changes.
   */
  interface RouletteChangeListener {
    /**
     * Called when user selects a new position in the wheel menu.

     * @param selectedPosition the new position selected.
     */
    fun onSelectionChange(selectedPosition: Int)
  }

  //listener for touch events on the wheel
  private inner class RouletteTouchListener : View.OnTouchListener {
    private var startAngle: Float = 0.toFloat()

    override fun onTouch(v: View, event: MotionEvent): Boolean {
     // Toast.makeText(context,"Me has tocado",Toast.LENGTH_LONG).show()
     // rotateWheel(Random().nextInt(2000).toFloat())
      when (event.action) {
        MotionEvent.ACTION_MOVE -> {
          //get the current angle for the current move event
          val currentAngle = getAngle(event.x.toDouble(), event.y.toDouble())

          //rotate the wheel by the difference
          rotateWheel((startAngle - currentAngle).toFloat()*1000)

          //current angle becomes start angle for the next motion

        }



      }

      return true
    }
  }
}

