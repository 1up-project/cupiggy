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

  private val fontSetImage = ImageHelper.getFontsImage()
  private val fontSize = 32

  constructor()

  /**
   * 文字を描画します。
   */
  fun drawText(text: String, x: Float, y: Float, canvas: Canvas) {
    val textByte = text.toByteArray()

    var drawX = x.toInt()
    var drawY = y.toInt()

    var keyCode: Int
    var fontX: Int
    var fontY: Int

    var drawRange = RectF()
    var fontRange = Rect()

    for (i in 0 until textByte.size) {
      keyCode = textByte[i].toInt() - 32

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
      fontRange.left = (fontX * fontSize) - fontSize
      fontRange.top = fontY * fontSize
      fontRange.right = fontRange.left + fontSize
      fontRange.bottom = fontRange.top + fontSize

      // 画像の表示位置
      drawRange.left = drawX.toFloat()
      drawRange.top = drawY.toFloat()
      drawRange.right = drawRange.left + fontSize
      drawRange.bottom = drawRange.top + fontSize

      canvas.drawBitmap(fontSetImage, fontRange, drawRange, null)

      drawX += fontSize
    }
  }

  /**
   * 中央揃えで描画します。
   */
  fun drawCenteringText(text: String, y: Float, canvas: Canvas) {
    val dispCenter = Utility.getGameWidth() / 2
    val stringWidth = text.length * fontSize
    val x = dispCenter - (stringWidth / 2)

    drawText(text, x, y, canvas)
  }
}