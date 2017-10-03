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
  private val isTop = Utility.getRandomIntValue(2) != 1

  private val referenceY = 190f

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
    val width = Utility.getRandomFloatValue(maxWidth.toInt() - minWidth.toInt())
    val height = Utility.getRandomFloatValue(maxHeight.toInt() - minHeight.toInt())

    this.width = minWidth + width
    this.height = minHeight + height

    x = startPointX
    y = bottomPoint - this.height + referenceY
    if (isTop) {
      y = 0f - referenceY
    }

    createCollisionRange(this.width, this.height)
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