package com.oneupproject.cupiggy.game

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import com.oneupproject.cupiggy.BuildConfig
import com.oneupproject.cupiggy.util.Utility

/**
 * FpsManager
 *
 * フレームレートを管理します。
 * 60FPSを保つよう制御します。
 *
 * @author y728n
 * @since 1.0.0
 */

object FpsManager : BasicManager() {

  var count = 0
    private set

  private var startTime: Long = 0
  private var fps = 0.0f

  private val FPS = 60

  private var paint = Paint()
  private val FONT_SIZE = 32.0f

  init {
    paint.color = Color.GREEN
    paint.textSize = FONT_SIZE
  }

  override fun onUpdate() : Boolean {
    if (count == 0) {
      startTime = System.currentTimeMillis()
    }

    if (count == FPS) {
      val TIME: Long = System.currentTimeMillis()
      fps = 1000.0f / ((TIME - startTime) / FPS)
    }
    count++

    return true
  }

  override fun onDraw(canvas : Canvas) {
    if (BuildConfig.IS_DEBUG) {
      val DRAW_X = 0f
      val DRAW_Y = Utility.getGameHeight() - FONT_SIZE

      canvas.drawText(String.format("%.1f fps", fps), DRAW_X, DRAW_Y, paint)
    }
  }

}

