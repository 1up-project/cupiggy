package com.oneupproject.cupiggy.game.actor.background

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import com.oneupproject.cupiggy.util.Utility
import com.oneupproject.cupiggy.util.helper.ImageHelper
import com.oneupproject.cupiggy.util.shape.RectDrawingShape

/**
 * MidnightBackground
 *
 * お昼の表現をした背景です。
 *
 * @author y728n
 * @since 1.0.0
 */

class SunnyBackgroundActor : BasicBackgroundActor {

  private val sunImage = ImageHelper.getSun()

  private var underPaint = Paint()
  private var underRect = RectDrawingShape()

  constructor() : super()

  init {
    underPaint.color = Color.rgb(0, 80, 160)
    underRect = RectDrawingShape(underPaint, 0f, 470f,
        Utility.getGameWidth(), Utility.getGameHeight() - 470f)
  }

  override fun onDraw(canvas: Canvas) {
    canvas.drawColor(Color.rgb(0, 104, 183))
    underRect.draw(canvas)
    canvas.drawBitmap(sunImage, 200f, 150f, paint)
  }

}