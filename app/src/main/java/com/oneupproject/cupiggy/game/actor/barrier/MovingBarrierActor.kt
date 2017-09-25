package com.oneupproject.cupiggy.game.actor.barrier

import android.graphics.Canvas
import com.oneupproject.cupiggy.game.FpsManager
import com.oneupproject.cupiggy.util.Utility
import com.oneupproject.cupiggy.util.shape.BarrierDrawingShape

/**
 * MovingBarrier
 *
 * 曲線的に移動する障害物です。
 *
 * @author y728n
 * @since 1.0.0
 */

class MovingBarrierActor : BasicBarrierActor {

  private val CYCLE = Utility.getRandomFloatValue(180) + 120f
  private val STROKE = Utility.getRandomFloatValue(200) + 100f

  private val SIZE = 60f

  private var shape = BarrierDrawingShape()

  init {
    minSpeed = 7f
    maxSpeed = 10f

    width = SIZE
    height = SIZE

    x = START_POINT_X
    y = Utility.getRandomY()

    vector.x = getSpeed()

    createCollisionRange(width, height)
  }

  constructor() : super()

  override fun move() {
    x += vector.x
//    y = (CYCLE + Math.sin(Math.PI * 2f / CYCLE * FpsManager.count) * STROKE).toFloat()
    y = Utility.getUpAndDownPoint(FpsManager.count, CYCLE, STROKE)
  }

  override fun onUpdate(): Boolean {
    super.onUpdate()

    shape.x = x
    shape.y = y
    shape.width = width
    shape.height = height

    return true
  }

  override fun onDraw(canvas: Canvas) {
    shape.draw(canvas)
  }

}