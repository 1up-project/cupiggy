package com.oneupproject.cupiggy.util.helper

import android.content.Context
import android.content.SharedPreferences

/**
 * PreferenceHelper
 *
 * Preferenceを扱います。
 *
 * @author y728n
 * @since 1.0.0
 */

object PreferenceHelper : BasicHelper {

  private var pref: SharedPreferences? = null
  private var editor: SharedPreferences.Editor? = null

  override fun onCreate(context: Context) {
    pref = context.getSharedPreferences("remon-36056215A2D55deA3acf", Context.MODE_PRIVATE)
    editor = pref?.edit()
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

  /**
   * ハイスコアを更新します。
   *
   * @param score 更新値
   */
  fun updateHighscore(score: Int) {
    if (editor == null) {
      return
    }
    editor!!.putInt("highscore", score)
    editor!!.commit()
  }

  /**
   * ハイスコアを読み込みます。
   *
   * @return ハイスコア
   */
  fun readHighscore(): Int {
    if (pref == null) {
      return 0
    }
    return pref!!.getInt("highscore", 0)
  }

}