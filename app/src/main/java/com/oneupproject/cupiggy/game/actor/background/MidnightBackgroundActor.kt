package com.oneupproject.cupiggy.game.actor.background

import android.graphics.Canvas
import android.graphics.Color
import com.oneupproject.cupiggy.game.actor.BasicActor
import com.oneupproject.cupiggy.util.Utility
import com.oneupproject.cupiggy.util.Vector
import com.oneupproject.cupiggy.util.helper.ImageHelper

/**
 * 深夜の表現をした背景です。
 *
 * @author y728n
 * @since 1.0.0
 */

class MidnightBackgroundActor : BasicBackgroundActor {

  private inner class Star : BasicActor {

    constructor(x: Float, y: Float, vector: Vector) {
      this.x = x
      this.y = y
      this.vector = vector
    }

    init {
      paint.color = Color.LTGRAY
      width = 1f
      height = 1f
    }

    override fun onDraw(canvas: Canvas) {
      canvas.drawPoint(x, y, paint)
    }
  }

  private val maxStarNum = 80

  private var stars: Array<Star?> = arrayOfNulls<Star?>(maxStarNum)

  private val moonImage = ImageHelper.getMoon()

  constructor() : super()

  init {
    for (i in 0 until maxStarNum) {
      stars[i] = getNewStar()
    }
  }

  private fun getNewStar(): Star {
    val x = Utility.getRandomFloatValue(Utility.getGameWidth().toInt())
    val y = Utility.getRandomFloatValue(Utility.getGameHeight().toInt())
    val vec = Vector(-5f, 0f)

    return Star(x, y, vec)
  }

  override fun onUpdate(): Boolean {
    for (i in 0 until maxStarNum) {
      stars[i]!!.onUpdate()
      if (!stars[i]!!.isAlive) {
        stars[i] = getNewStar()
      }
    }

    return true
  }

  override fun onDraw(canvas: Canvas) {
    canvas.drawColor(Color.rgb(0, 1, 34))
    stars.forEach { it!!.onDraw(canvas) }
    canvas.drawBitmap(moonImage, 300f, 280f, paint)
  }

}