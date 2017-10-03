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

  private val cycle = Utility.getRandomFloatValue(180) + 120f
  private val stroke = Utility.getRandomFloatValue(200) + 100f

  private val size = 60f

  private var shape = BarrierDrawingShape()

  init {
    minSpeed = 7f
    maxSpeed = 10f

    width = size
    height = size

    x = startPointX
    y = Utility.getRandomY()

    vector.x = getSpeed()

    createCollisionRange(width, height)
  }

  constructor() : super()

  override fun move() {
    x += vector.x
    y = Utility.getUpAndDownPoint(FpsManager.count, cycle, stroke)
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