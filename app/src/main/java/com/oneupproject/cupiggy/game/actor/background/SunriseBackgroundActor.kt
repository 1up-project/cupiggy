package com.oneupproject.cupiggy.game.actor.background

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import com.oneupproject.cupiggy.util.Utility
import com.oneupproject.cupiggy.util.helper.ImageHelper
import com.oneupproject.cupiggy.util.shape.RectDrawingShape

/**
 * SunriseBackground
 *
 * 日の出の表現をした背景です。
 *
 * @author y728n
 * @since 1.0.0
 */

class SunriseBackgroundActor : BasicBackgroundActor {

  private val SUNRISE_IMAGE = ImageHelper.getSunrise()

  private var underPaint = Paint()
  private var underRect = RectDrawingShape()

  constructor() : super()

  init {
    underPaint.color = Color.rgb(0, 0, 80)
    underRect = RectDrawingShape(underPaint, 0f, 490f,
        Utility.getGameWidth(), Utility.getGameHeight() - 490f)
  }

  override fun onDraw(canvas: Canvas) {
    canvas.drawColor(Color.rgb(181, 181, 182))
    underRect.draw(canvas)
    canvas.drawBitmap(SUNRISE_IMAGE, 300f, 300f, paint)
  }

}