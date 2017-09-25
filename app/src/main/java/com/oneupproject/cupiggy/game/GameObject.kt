package com.oneupproject.cupiggy.game

import android.graphics.Canvas

/**
 * GameObject
 *
 * ゲーム中の全オブジェクトの基底クラスです。
 * 更新、描画処理のみを持ちます。
 *
 * @author y728n
 * @since 1.0.0
 */

interface GameObject {
  fun onUpdate(): Boolean = true
  fun onDraw(canvas: Canvas)
}