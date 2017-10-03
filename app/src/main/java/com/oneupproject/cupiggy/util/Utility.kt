package com.oneupproject.cupiggy.util

import android.util.Log
import com.oneupproject.cupiggy.game.manager.GameManager
import com.oneupproject.cupiggy.game.manager.ScoreManager
import java.security.SecureRandom

/**
 * Utility
 *
 * いわゆるUtilクラスです。
 *
 * @author y728n
 * @since 1.0.0
 */

object Utility {

  fun getGameWidth() = GameManager.WIDTH
  fun getGameHeight() = GameManager.HEIGHT

  /**
   * スコアに応じた難易度を返します。
   *
   * @param 判定に使用する点数
   * @return GameManager.Difficulty
   */
  fun getNowDifficulty(): GameManager.Difficulty {
    val score = ScoreManager.score

    return when {
      (score < GameManager.Difficulty.EASY.gradePoint) -> {
        GameManager.Difficulty.VERY_EASY
      }
      (score < GameManager.Difficulty.NORMAL.gradePoint) -> {
        GameManager.Difficulty.EASY
      }
      (score < GameManager.Difficulty.HARD.gradePoint) -> {
        GameManager.Difficulty.NORMAL
      }
      (score < GameManager.Difficulty.VERY_HARD.gradePoint) -> {
        GameManager.Difficulty.HARD
      }
      else -> {
        GameManager.Difficulty.VERY_HARD
      }
    }
  }

  /**
   * 乱数をInt型で返します。
   *
   * @param range 生成したい値の範囲をInt型で指定
   * @return 生成した値
   */
  fun getRandomIntValue(range: Int): Int {

    var randValue = 0

    try {
      val RAND = SecureRandom.getInstance("SHA1PRNG")
      randValue = RAND.nextInt(range)
    } catch (e: Throwable) {
      Log.d("RANDOM", e.message)
    } finally {
      return randValue
    }

  }

  /**
   * 乱数をFloat型で返します。
   *
   * @param range 生成したい値の範囲をInt型で指定
   * @return 生成した値
   */
  fun getRandomFloatValue(range: Int): Float {
    return getRandomIntValue(range).toFloat()
  }

  /**
   * ランダムでY座標を設定します。
   */
  fun getRandomY(height: Float = 0f, center: Float = 0f): Float {
    var seed = getGameHeight().toInt()

    if (height != 0f) {
      seed = height.toInt()
    }

    return getRandomFloatValue(seed) + center
  }

  /**
   * sin波を返します。
   */
  fun getUpAndDownPoint(count: Int, cycle: Float, stroke: Float): Float {
    if (count == 0 || cycle == 0f || stroke == 0f) {
      return 0f
    }
    return (cycle + Math.sin(Math.PI * 2f / cycle * count) * stroke).toFloat()
  }

}