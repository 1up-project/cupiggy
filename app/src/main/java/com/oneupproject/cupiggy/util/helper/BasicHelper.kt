package com.oneupproject.cupiggy.util.helper

import android.content.Context

/**
 * BasicHandler
 *
 * Handlerの基底クラスです。
 * リソースやセンサーなどを使うためのクラスなので、
 * Activityの状態により連動させる想定です。
 *
 * @author y728n
 * @since 1.0.0
 */

interface BasicHelper {

  fun onCreate(context: Context)
  fun onResume()
  fun onPause()
  fun onDestroy()

}