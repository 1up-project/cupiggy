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

  private val MAX_STAR_NUM = 80

  private var stars: Array<Star?> = arrayOfNulls<Star?>(MAX_STAR_NUM)

  private val MOON_IMAGE = ImageHelper.getMoon()

  constructor() : super()

  init {
    for (i in 0 until MAX_STAR_NUM) {
      stars[i] = getNewStar()
    }
  }

  private fun getNewStar(): Star {
    val X = Utility.getRandomFloatValue(Utility.getGameWidth().toInt())
    val Y = Utility.getRandomFloatValue(Utility.getGameHeight().toInt())
    val VECTOR = Vector(-5f, 0f)

    return Star(X, Y, VECTOR)
  }

  override fun onUpdate(): Boolean {
    for (i in 0 until MAX_STAR_NUM) {
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
    canvas.drawBitmap(MOON_IMAGE, 300f, 280f, paint)
  }

}