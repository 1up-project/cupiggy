package com.oneupproject.cupiggy.util.helper

import android.content.Context

/**
 * TouchHelper
 *
 * タッチスクリーンの状態の受渡を行います。
 *
 * @author y728n
 * @since 1.0.0
 */

object TouchHelper : BasicHelper {

  var x = 0f
    private set
  var y = 0f
    private set

  var isTouch = false
    private set

  override fun onCreate(context: Context) {
    return
  }

  override fun onResume() {
    return
  }

  override fun onPause() {
    return
  }

  override fun onDestroy() {
    return
  }

  fun clearTouchState() {
    isTouch = false
  }

  fun touchDown(x: Float, y: Float) {
    isTouch = true
    this.x = x
    this.y = y
  }

  fun touchUp() {
    isTouch = false
    x = 0f
    y = 0f
  }

}