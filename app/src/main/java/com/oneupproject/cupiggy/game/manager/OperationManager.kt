package com.oneupproject.cupiggy.game.manager

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import com.oneupproject.cupiggy.BuildConfig
import com.oneupproject.cupiggy.game.BasicManager
import com.oneupproject.cupiggy.util.Utility
import com.oneupproject.cupiggy.util.helper.GyroSensorHelper
import com.oneupproject.cupiggy.util.Vector
import com.oneupproject.cupiggy.util.helper.TouchHelper

/**
 * OperationManager
 *
 * デバイスへの操作情報を管理します。
 *
 * @property x x軸
 * @property y y軸
 * @property z z軸
 * @property vector ベクトル
 * @author y728n
 * @since 1.0.0
 */
object OperationManager : BasicManager() {

  var x: Float = 0.0f
    private set
  var y: Float = 0.0f
    private set
  var z: Float = 0.0f
    private set
  var vector: Vector = Vector()
    private set

  private var paint: Paint = Paint()
  private val fontSize: Float = 32.0f

  init {
    paint.color = Color.YELLOW
    paint.textSize = fontSize
  }

  fun clearInputState() {
    x = 0f
    y = 0f
    z = 0f

    TouchHelper.clearTouchState()
  }

  fun isTouching(): Boolean {
    return TouchHelper.isTouch
  }

  override fun onUpdate(): Boolean {
    x = -GyroSensorHelper.y / GameManager.displayScaleY
    y = GyroSensorHelper.x / GameManager.displayScaleX
    z = GyroSensorHelper.z

    var x: Float = -x * 1.2f
    var y: Float = y * 1.2f

    vector.x = if (x < 0) -x * x else x * x
    vector.y = if (y < 0) -y * y else y * y

    return true
  }

  override fun onDraw(canvas: Canvas) {
    if (BuildConfig.IS_DEBUG) {
      val drawX = Utility.getGameWidth() - 120f
      val drawY = Utility.getGameHeight()

      canvas.drawText(String.format("x: %.1f", x), drawX, drawY - fontSize * 3 - 2f, paint)
      canvas.drawText(String.format("y: %.1f", y), drawX, drawY - fontSize * 2 - 2f, paint)
      canvas.drawText(String.format("z: %.1f", z), drawX, drawY - fontSize * 1 - 2f, paint)
    }
  }
}