package com.oneupproject.cupiggy.util.shape

import android.graphics.Canvas
import android.graphics.Paint

/**
 * BasicDrawingShape
 *
 * DrawingShapeの基底クラスです。
 * Canvasで描画します。
 *
 * @property x 表示位置
 * @property y 表示位置
 *
 * @author y728n
 * @since 1.0.0
 */

abstract class BasicDrawingShape {

  var x = 0f
  var y = 0f

  protected var paint = Paint()

  constructor()

  constructor(x: Float, y: Float) {
    this.x = x
    this.y = y
  }

  constructor(paint: Paint, x: Float, y: Float) : this(x, y) {
    this.paint = paint
  }

  abstract fun draw(canvas: Canvas)

}
