package com.oneupproject.cupiggy.util.shape

import android.graphics.Canvas
import android.graphics.Paint

/**
 * CircleDrawing
 *
 * 円を描くための情報を保持します。
 *
 * @property r 直径
 *
 * @author y728n
 * @since 1.0.0
 */

class CircleDrawingShape : BasicDrawingShape {

  var r = 0f

  constructor(paint: Paint, x: Float, y: Float, r: Float) : super(paint, x, y) {
    this.r = r
  }

  override fun draw(canvas: Canvas) {
    canvas.drawCircle(x, y, r, paint)
  }

}
