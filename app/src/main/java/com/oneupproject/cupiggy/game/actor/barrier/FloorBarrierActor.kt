package com.oneupproject.cupiggy.game.actor.barrier

import android.graphics.Canvas
import com.oneupproject.cupiggy.game.manager.GameManager
import com.oneupproject.cupiggy.util.Utility
import com.oneupproject.cupiggy.util.shape.BarrierDrawingShape

/**
 * FloorBarrier
 *
 * 床型障害物です。
 *
 * @author y728n
 * @since 1.0.0
 */

open class FloorBarrierActor : BasicBarrierActor {

  /**
   * 角丸の矩形を使用するため、y座標を画面外に設定する基準の長さ
   */
  protected val REFERENCE_Y = 100f

  private val WIDTH = 51f
  private var minHeight = 10f
  private var maxHeight = 100f

  private var shape = BarrierDrawingShape()

  init {
    vector.x = -1f

    createBarrier()
  }

  constructor() : super()


  protected open fun getReferencePointY(): Float = BOTTOM_POINT - height + REFERENCE_Y

  private fun createBarrier() {
    width = WIDTH

    val NOW_DIFFICULTY = Utility.getNowDifficulty()

    when (NOW_DIFFICULTY) {
      (GameManager.Difficulty.VERY_EASY) -> {
        minHeight = 0f
        maxHeight = 0f
      }
      (GameManager.Difficulty.EASY) -> {
        minHeight = 50f
        maxHeight = 100f
      }
      (GameManager.Difficulty.NORMAL) -> {
        minHeight = 100f
        maxHeight = 150f
      }
      (GameManager.Difficulty.HARD) -> {
        minHeight = 150f
        maxHeight = 200f
      }
      else -> {
        minHeight = 200f
        maxHeight = 250f
      }
    }

    // 画面外にずらした分だけ追加
    minHeight += REFERENCE_Y
    maxHeight += REFERENCE_Y

    val HEIGHT = Utility.getRandomFloatValue(maxHeight.toInt() - minHeight.toInt())
    height = minHeight + HEIGHT

    x = START_POINT_X
    y = getReferencePointY()

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
