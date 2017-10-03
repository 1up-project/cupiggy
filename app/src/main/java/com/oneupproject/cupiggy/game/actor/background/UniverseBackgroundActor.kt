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

  private val speedOfWidth = 10f
  private val maxStarNum = 80

  private var shootingStars: Array<ShootingStar?> = arrayOfNulls<ShootingStar?>(maxStarNum)

  private val fivePointStarImage = ImageHelper.getFivePointStar()

  constructor() : super()

  init {
    for (i in 0 until maxStarNum) {
      shootingStars[i] = getNewStar()
    }
  }

  private fun getNewStar(): ShootingStar {
    val x = Utility.getRandomFloatValue(Utility.getGameWidth().toInt())
    val y = Utility.getRandomFloatValue(Utility.getGameHeight().toInt())

    val minSpeed = 5f
    val vec = Vector(-Utility.getRandomFloatValue(speedOfWidth.toInt()) - minSpeed, 0f)

    return ShootingStar(x, y, vec)
  }

  override fun onUpdate(): Boolean {
    for (i in 0 until maxStarNum) {
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
    canvas.drawBitmap(fivePointStarImage, 300f, 280f, paint)
  }

}