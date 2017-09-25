package com.oneupproject.cupiggy.util.shape

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF

/**
 * RectDrawing
 *
 * 矩形を描くための情報を保持します。
 *
 * @property width 横幅
 * @property height 縦幅
 *
 * @author y728n
 * @since 1.0.0
 */

open class RectDrawingShape : BasicDrawingShape {

  var width = 0.0f
  var height = 0.0f

  protected var rect = RectF()

  constructor() : super()

  constructor(paint: Paint, x: Float, y: Float, width: Float, height: Float) : super(paint, x, y) {
    this.width = width
    this.height = height

    updatePoints()
  }

  protected fun updatePoints() {
    rect.left = x
    rect.top = y
    rect.right = x + width
    rect.bottom = y + height
  }

  override fun draw(canvas: Canvas) {
    updatePoints()
    canvas.drawRect(rect, paint)
  }

}