package com.oneupproject.cupiggy.game.actor.barrier

import android.graphics.Canvas
import com.oneupproject.cupiggy.util.Utility
import com.oneupproject.cupiggy.util.shape.BarrierDrawingShape

/**
 * WallBarrier
 *
 * 壁型障害物です。
 *
 * @author y728n
 * @since 1.0.0
 */

open class WallBarrierActor : BasicBarrierActor {
  private val IS_TOP = Utility.getRandomIntValue(2) != 1

  private val REFERENCE_Y = 190f

  private var minWidth = 80f
  private var maxWidth = 130f
  private var minHeight = 300f
  private var maxHeight = 510f

  private var shape = BarrierDrawingShape()

  init {
    minSpeed = 4f
    maxSpeed = 5f

    vector.x = getSpeed()
    vector.y = 0f

    createWall()
  }

  constructor() : super()

  open protected fun createWall() {
    val WIDTH = Utility.getRandomFloatValue(maxWidth.toInt() - minWidth.toInt())
    val HEIGHT = Utility.getRandomFloatValue(maxHeight.toInt() - minHeight.toInt())

    width = minWidth + WIDTH
    height = minHeight + HEIGHT

    x = START_POINT_X
    y = BOTTOM_POINT - height + REFERENCE_Y
    if (IS_TOP) {
      y = 0f - REFERENCE_Y
    }

    createCollisionRange(width, height)
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