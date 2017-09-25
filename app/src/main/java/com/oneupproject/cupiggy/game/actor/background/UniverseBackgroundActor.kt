package com.oneupproject.cupiggy.game.actor.background

import android.graphics.Canvas
import android.graphics.Color
import com.oneupproject.cupiggy.game.actor.BasicActor
import com.oneupproject.cupiggy.util.Utility
import com.oneupproject.cupiggy.util.Vector
import com.oneupproject.cupiggy.util.helper.ImageHelper

/**
 * UniverseBackground
 *
 * 宇宙のような表現の背景です。
 *
 * @author y728n
 * @since 1.0.0
 */

class UniverseBackgroundActor : BasicBackgroundActor {

  private inner class ShootingStar : BasicActor {

    constructor(x: Float, y: Float, vector: Vector) {
      this.x = x
      this.y = y
      this.vector = vector
    }

    init {
      paint.color = Color.WHITE
      width = 1f
      height = 1f
    }

    override fun onDraw(canvas: Canvas) {
      canvas.drawPoint(x, y, paint)
    }
  }

  private val MIN_SPEED = 5f
  private val SPEED_OF_WIDTH = 10f
  private val MAX_STAR_NUM = 80

  private var shootingStars: Array<ShootingStar?> = arrayOfNulls<ShootingStar?>(MAX_STAR_NUM)

  private val FIVE_POINT_STAR_IMAGE = ImageHelper.getFivePointStar()

  constructor() : super()

  init {
    for (i in 0 until MAX_STAR_NUM) {
      shootingStars[i] = getNewStar()
    }
  }

  private fun getNewStar(): ShootingStar {
    val X = Utility.getRandomFloatValue(Utility.getGameWidth().toInt())
    val Y = Utility.getRandomFloatValue(Utility.getGameHeight().toInt())
    val VECTOR = Vector(-Utility.getRandomFloatValue(SPEED_OF_WIDTH.toInt()) - MIN_SPEED, 0f)

    return ShootingStar(X, Y, VECTOR)
  }

  override fun onUpdate(): Boolean {
    for (i in 0 until MAX_STAR_NUM) {
      shootingStars[i]!!.onUpdate()
      if (!shootingStars[i]!!.isAlive) {
        shootingStars[i] = getNewStar()
      }
    }

    return true
  }

  override fun onDraw(canvas: Canvas) {
    canvas.drawColor(Color.BLACK)
    shootingStars.forEach { it!!.onDraw(canvas) }
    canvas.drawBitmap(FIVE_POINT_STAR_IMAGE, 300f, 280f, paint)
  }

}