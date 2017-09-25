package com.oneupproject.cupiggy.util

import android.graphics.Canvas
import android.graphics.Rect
import android.graphics.RectF
import com.oneupproject.cupiggy.util.helper.ImageHelper

/**
 * Font
 *
 * ゲーム用のフォントで文字を描画します。
 * 文字列をAsciiコードに変換し、下記表の通りに文字を描画します。
 * そのため、下記表にない文字の描画はすべてスペースに変換されます。
 *
 *   1234567890123
 * 1   "#$%&'  *+,-
 * 2 ./0123456789:
 * 3 ;<=>?@ABCDEFG
 * 4 HIJKLMNOPQRST
 * 5 UVWXYZ
 *
 * @author y728n
 * @since 1.0.0
 */

class Font {

  private val FONT_SET_IMAGE = ImageHelper.getFontsImage()
  private val FONT_SIZE = 32

  constructor()

  /**
   * 文字を描画します。
   */
  fun drawText(text: String, x: Float, y: Float, canvas: Canvas) {
    val TEXT_BYTE = text.toByteArray()

    var drawX = x.toInt()
    var drawY = y.toInt()

    var keyCode: Int
    var fontX: Int
    var fontY: Int

    var drawRange = RectF()
    var fontRange = Rect()

    for (i in 0 until TEXT_BYTE.size) {
      keyCode = TEXT_BYTE[i].toInt() - 32

      when {
        keyCode < 0 -> {
          keyCode = 0
        }
        keyCode > 58 -> {
          keyCode = 0
        }
      }

      fontX = keyCode % 13
      fontY = keyCode / 13

      if (fontX == 0) {
        fontX = 13
        fontY -= 1
      }

      // 画像の切り出し範囲
      fontRange.left = (fontX * FONT_SIZE) - FONT_SIZE
      fontRange.top = fontY * FONT_SIZE
      fontRange.right = fontRange.left + FONT_SIZE
      fontRange.bottom = fontRange.top + FONT_SIZE

      // 画像の表示位置
      drawRange.left = drawX.toFloat()
      drawRange.top = drawY.toFloat()
      drawRange.right = drawRange.left + FONT_SIZE
      drawRange.bottom = drawRange.top + FONT_SIZE

      canvas.drawBitmap(FONT_SET_IMAGE, fontRange, drawRange, null)

      drawX += FONT_SIZE
    }
  }

  /**
   * 中央揃えで描画します。
   */
  fun drawCenteringText(text: String, y: Float, canvas: Canvas) {
    val DISP_CENTER = Utility.getGameWidth() / 2
    val STRING_WIDTH = text.length * FONT_SIZE
    val X = DISP_CENTER - (STRING_WIDTH / 2)

    drawText(text, X, y, canvas)
  }
}