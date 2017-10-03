package com.oneupproject.cupiggy.game.manager

import android.graphics.Canvas
import android.graphics.Color
import com.oneupproject.cupiggy.game.BasicManager
import com.oneupproject.cupiggy.game.FpsManager
import com.oneupproject.cupiggy.game.SceneManager

/**
 * GameManager
 *
 * 各Managerクラスを管理します。
 *
 * @property WIDTH ゲームの横幅
 * @property HEIGHT ゲームの縦幅
 * @property displayWidth ディスプレイの横幅
 * @property displayHeight ディスプレイの縦幅fontSetImage
 * @property displayScaleX x軸倍率
 * @property displayScaleY y軸倍率
 * @author y728n
 * @since 1.0.0
 */

object GameManager : BasicManager() {

  const val WIDTH: Float = 1280.0f
  const val HEIGHT: Float = 720.0f

  var displayWidth: Float = 0.0f
  var displayHeight: Float = 0.0f
  var displayScaleX: Float = 0.0f
  var displayScaleY: Float = 0.0f

  enum class Difficulty(val gradePoint: Int) {
    NONE(0),
    VERY_EASY(0),
    EASY(25000),
    NORMAL(80000),
    HARD(150000),
    VERY_HARD(250000),
  }

  override fun onUpdate(): Boolean {

    SceneManager.onUpdate()
    FpsManager.onUpdate()
    ScoreManager.onUpdate()
    OperationManager.onUpdate()

    return true
  }

  override fun onDraw(canvas: Canvas) {
    canvas.drawColor(Color.BLACK)

    SceneManager.onDraw(canvas)
    FpsManager.onDraw(canvas)
    ScoreManager.onDraw(canvas)
    OperationManager.onDraw(canvas)

  }

}
