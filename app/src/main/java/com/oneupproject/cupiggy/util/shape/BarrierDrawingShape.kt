package com.oneupproject.cupiggy.util.shape

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF

/**
 * BarrierDrawing
 *
 * 障害物を描画します。
 *
 * @author y728n
 * @since 1.0.0
 */

open class BarrierDrawingShape : BasicDrawingShape {

  var width = 0f
  var height = 0f
  var rx = 12f
  var ry = 12f

  private var baseRect = RectF()
  private var innerRect = RectF()

  private var basePaint = Paint()
  private var innerPaint = Paint()

  init {
    basePaint.color = Color.DKGRAY
    basePaint.isAntiAlias = true

    innerPaint.color = Color.GRAY
    innerPaint.isAntiAlias = true
  }

  constructor() : super()

  constructor(x: Float, y: Float, width: Float, height: Float) : super(x, y) {
    this.width = width
    this.height = height
  }

  constructor(x: Float, y: Float, width: Float, height: Float, rx: Float, ry: Float)
      : this(x, y, width, height) {
    this.rx = rx
    this.ry = ry
  }

  private fun updatePoints() {
    baseRect.left = x
    baseRect.top = y
    baseRect.right = x + width
    baseRect.bottom = y + height

    innerRect.left = baseRect.left + rx
    innerRect.right = baseRect.right - rx
    innerRect.top = baseRect.top + ry
    innerRect.bottom = baseRect.bottom - ry
  }

  override fun draw(canvas: Canvas) {
    updatePoints()

    canvas.drawRoundRect(baseRect, rx, ry, basePaint)
    canvas.drawRoundRect(innerRect, rx, ry, innerPaint)
  }

}