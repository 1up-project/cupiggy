package com.oneupproject.cupiggy.game.actor.barrier

import com.oneupproject.cupiggy.game.FpsManager
import com.oneupproject.cupiggy.util.Utility

/**
 * FloatingWallBarrier
 *
 * 上下に浮遊する壁型障害物です。
 *
 * @author y728n
 * @since 1.0.0
 */

class FloatingWallBarrierActor : WallBarrierActor {

  private val cycle = Utility.getRandomFloatValue(240) + 120f
  private val stroke = Utility.getRandomFloatValue(140) + 100f

  private var minWidth = 80f
  private var maxWidth = 100f
  private var minHeight = 150f
  private var maxHeight = 250f

  init {
    minSpeed = 4f
    maxSpeed = 5f

    vector.x = getSpeed()
    vector.y = 0f

    createWall()
  }

  constructor() : super()

  override fun createWall() {
    val width = Utility.getRandomFloatValue(maxWidth.toInt() - minWidth.toInt())
    val height = Utility.getRandomFloatValue(maxHeight.toInt() - minHeight.toInt())

    this.width = minWidth + width
    this.height = minHeight + height

    x = startPointX

    createCollisionRange(this.width, this.height)
  }

  override fun move() {
    x += vector.x
    y = Utility.getUpAndDownPoint(FpsManager.count, cycle, stroke) + 100f
  }

}