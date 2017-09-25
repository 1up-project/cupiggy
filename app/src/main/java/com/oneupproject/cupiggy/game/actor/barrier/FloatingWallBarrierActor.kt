package com.oneupproject.cupiggy.game.actor.barrier

import com.oneupproject.cupiggy.game.FpsManager
import com.oneupproject.cupiggy.util.Utility
import com.oneupproject.cupiggy.util.shape.BarrierDrawingShape

/**
 * FloatingWallBarrier
 *
 * 上下に浮遊する壁型障害物です。
 *
 * @author y728n
 * @since 1.0.0
 */

class FloatingWallBarrierActor : WallBarrierActor {

  private val CYCLE = Utility.getRandomFloatValue(240) + 120f
  private val STROKE = Utility.getRandomFloatValue(140) + 100f

  private var minWidth = 80f
  private var maxWidth = 100f
  private var minHeight = 150f
  private var maxHeight = 250f

  private var shape = BarrierDrawingShape()

  init {
    minSpeed = 4f
    maxSpeed = 5f

    vector.x = getSpeed()
    vector.y = 0f

    createWall()
  }

  constructor() : super()

  override fun createWall() {
    val WIDTH = Utility.getRandomFloatValue(maxWidth.toInt() - minWidth.toInt())
    val HEIGHT = Utility.getRandomFloatValue(maxHeight.toInt() - minHeight.toInt())

    width = minWidth + WIDTH
    height = minHeight + HEIGHT

    x = START_POINT_X

    createCollisionRange(width, height)
  }

  override fun move() {
    x += vector.x
    y = Utility.getUpAndDownPoint(FpsManager.count, CYCLE, STROKE) + 100f
  }

}