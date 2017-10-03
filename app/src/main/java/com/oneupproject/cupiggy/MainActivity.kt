package com.oneupproject.cupiggy

import android.app.Activity
import android.content.pm.ActivityInfo
import android.os.Build
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.view.WindowManager
import com.oneupproject.cupiggy.game.GameView
import com.oneupproject.cupiggy.util.helper.*

class MainActivity : Activity() {

  private var gameView: GameView? = null

  /**
   * Main
   */
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    initialize()

    createHandlers()
    createGameView()

  }

  override fun onResume() {
    super.onResume()
    resumeHandlers()
  }

  override fun onPause() {
    super.onPause()
    pauseHandlers()
  }

  override fun onDestroy() {
    super.onDestroy()
    destroyHandlers()
  }

  /**
   * Activity初期化処理
   */
  private fun initialize() {
    // 縦画面
    super.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE)

    // スリープさせない
    window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)

    // 全画面表示
    if (Build.VERSION.SDK_INT >= 19) {
      val decorView = window.decorView
      decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN or
          View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or
          View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY or
          View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or
          View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION or
          View.SYSTEM_UI_FLAG_LAYOUT_STABLE
    }
  }

  override fun onTouchEvent(event: MotionEvent?): Boolean {
    when (event?.action) {
      MotionEvent.ACTION_DOWN -> {
        TouchHelper.touchDown(event?.x, event?.y)
      }
      MotionEvent.ACTION_CANCEL, MotionEvent.ACTION_UP -> {
        TouchHelper.touchUp()
      }
    }
    return true
  }

  private fun createGameView() {
    gameView = GameView(this)
    setContentView(gameView)
  }

  private fun createHandlers() {
    GyroSensorHelper.onCreate(this)
    TouchHelper.onCreate(this)
    ImageHelper.onCreate(this)
    PreferenceHelper.onCreate(this)
    MusicHelper.onCreate(this)
    SoundHelper.onCreate(this)
  }

  private fun resumeHandlers() {
    GyroSensorHelper.onResume()
    TouchHelper.onResume()
    ImageHelper.onResume()
    PreferenceHelper.onResume()
    MusicHelper.onResume()
    SoundHelper.onResume()
  }

  private fun pauseHandlers() {
    GyroSensorHelper.onPause()
    TouchHelper.onPause()
    ImageHelper.onPause()
    PreferenceHelper.onResume()
    MusicHelper.onPause()
    SoundHelper.onPause()
  }

  private fun destroyHandlers() {
    GyroSensorHelper.onDestroy()
    TouchHelper.onDestroy()
    ImageHelper.onDestroy()
    PreferenceHelper.onDestroy()
    MusicHelper.onDestroy()
    SoundHelper.onDestroy()
  }

}
