package com.oneupproject.cupiggy.game.actor.background

import android.graphics.Canvas
import android.graphics.Color
import com.oneupproject.cupiggy.game.actor.BasicActor
import com.oneupproject.cupiggy.util.Utility
import com.oneupproject.cupiggy.util.Vector
import com.oneupproject.cupiggy.util.helper.ImageHelper

/**
 * CloudyBackground
 *
 * 雨の状態を表現した背景です。
 *
 * @author y728n
 * @since 1.0.0
 */

class CloudyBackgroundActor : BasicBackgroundActor {

  private inner class Rain : BasicActor {

    constructor(x: Float, y: Float, vector: Vector) {
      this.x = x
      this.y = y
      this.vector = vector
    }

    init {
      paint.color = Color.LTGRAY
      width = 1f
      height = Utility.getRandomFloatValue(30) + 30
    }

    override fun onDraw(canvas: Canvas) {
      canvas.drawRect(x, y, x + width, y + height, paint)
    }

  }

  private val cloudImage = ImageHelper.getCloud()

  private val speedOfWidth = 50f
  private val maxRainNum = 80

  private var rains: Array<Rain?> = arrayOfNulls<Rain?>(maxRainNum)

  constructor() : super()

  init {
    for (i in 0 until maxRainNum) {
      rains[i] = getNewRain()
    }
  }

  private fun getNewRain(): Rain {
    val x = Utility.getRandomFloatValue(Utility.getGameWidth().toInt())
    val y = -10f

    val minSpeed = 30f
    val vec = Vector(1f, Utility.getRandomFloatValue(speedOfWidth.toInt()) + minSpeed)

    return Rain(x, y, vec)
  }

  override fun onUpdate(): Boolean {
    for (i in 0 until maxRainNum) {
      rains[i]!!.onUpdate()
      if (!rains[i]!!.isAlive) {
        rains[i] = getNewRain()
      }
    }

    return true
  }

  override fun onDraw(canvas: Canvas) {
    canvas.drawColor(Color.rgb(62, 58, 57))
    rains.forEach { it!!.onDraw(canvas) }
    canvas.drawBitmap(cloudImage, 250f, 200f, paint)
  }

}