package com.oneupproject.cupiggy.util

/**
 * Vector
 *
 * 移動方向などのベクトル情報を保持するクラスです。
 *
 * @property x x軸
 * @property y y軸
 *
 * @author y728n
 * @since 1.0.0
 */

class Vector {

  var x: Float = 0.toFloat()
  var y: Float = 0.toFloat()

  constructor()

  constructor(x: Float, y: Float) {
    this.x = x
    this.y = y
  }

  /**
   * 角度を取得する
   * @return 角度
   */
  private val angle: Float
    get() = Math.atan2(y.toDouble(), x.toDouble()).toFloat()

  /**
   * 大きさを取得する
   * @return 大きさ
   */
  private val length: Float
    get() = Math.sqrt((x * x + y * y).toDouble()).toFloat()

  /**
   * 引数の値より大きければ引数の値にする
   */
  fun restrict(maxLength: Float) {
    val length = length

    if (maxLength == 0f) {
      return
    }

    if (length > maxLength) {
      val rate = length / maxLength
      x /= rate
      y /= rate
    }
  }

  /**
   * vector方向の向きへrate率ほどブレンドする
   */
  fun blend(vector: Vector, rate: Float) {
    val width = vector.x - x
    val height = vector.y - y

    x += width * rate
    y += height * rate
  }

}
