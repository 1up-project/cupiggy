package com.oneupproject.cupiggy.game

import android.content.Context
import android.graphics.Canvas
import android.util.Log
import android.view.SurfaceHolder
import android.view.SurfaceView
import com.oneupproject.cupiggy.game.manager.GameManager

/**
 * GameView
 *
 * ゲーム用Viewクラスです。
 * SurfaceViewを継承します。
 * change!!
 *
 * @property thread スレッドクラス
 * @author y728n
 * @since 1.0.0
 */

class GameView(context: Context) : SurfaceView(context), SurfaceHolder.Callback, Runnable {

  private var thread: Thread? = null

  init {
    holder.addCallback(this)
  }

  override fun surfaceCreated(holder: SurfaceHolder?) {
    if (holder != null) {
      thread = Thread(this)
      thread?.start()
    }

    try {
      // 画面サイズ
      GameManager.displayWidth = super.getWidth().toFloat()
      GameManager.displayHeight = super.getHeight().toFloat()

      // dip変換
      GameManager.displayScaleX = GameManager.displayWidth / GameManager.WIDTH
      GameManager.displayScaleY = GameManager.displayHeight / GameManager.HEIGHT

      // Scene設定
      SceneManager.changeScene(SceneManager.SceneType.TITLE)
    } catch (e: NoClassDefFoundError) {
      Log.d("Initialize error", e.message)
    }

  }

  override fun run() {
    while (thread != null) {
      doUpdate()
      doDraw(holder)
    }
  }

  override fun surfaceChanged(holder: SurfaceHolder?, format: Int, width: Int, height: Int) {
  }

  override fun surfaceDestroyed(holder: SurfaceHolder?) {
    thread = null
  }

  private fun doUpdate() {
    GameManager.onUpdate()
  }

  private fun doDraw(holder: SurfaceHolder) {
    try {
      val canvas: Canvas? = holder.lockCanvas() ?: return

      // スクリーンに合わせて拡大縮小
      canvas?.scale(GameManager.displayScaleX, GameManager.displayScaleY)

      GameManager.onDraw(canvas!!)

      holder.unlockCanvasAndPost(canvas)
    } catch (e: Exception) {
      Log.e(this.javaClass.name, "doDraw", e)
    }
  }


}