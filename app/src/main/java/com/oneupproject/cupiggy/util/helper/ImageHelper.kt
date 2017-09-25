package com.oneupproject.cupiggy.util.helper

import android.content.Context
import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.oneupproject.cupiggy.R

/**
 * ImageHelper
 *
 * 画像ファイルを読み込み、返します。
 * 返すのはBitmapクラスのみです。
 *
 * @author y728n
 * @since 1.0.0
 */

object ImageHelper : BasicHelper {

  private var resource: Resources? = null

  override fun onCreate(context: Context) {
    resource = context.resources
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
   * フォント用のチップ画像をもとのサイズにリサイズして返します。
   * @return リサイズした画像を返します。
   */
  fun getFontsImage(): Bitmap {
    return Bitmap.createScaledBitmap(getResource(R.mipmap.img_font), 416, 160, false)
  }

  fun getPlayerImage(): Bitmap {
    return getResource(R.mipmap.img_player)
  }

  fun getItemStrawberry(): Bitmap {
    return getResource(R.mipmap.img_strawberry)
  }

  fun getItemPudding(): Bitmap {
    return getResource(R.mipmap.img_pudding)
  }

  fun getItemIcePop(): Bitmap {
    return getResource(R.mipmap.img_ice)
  }

  fun getItemDango(): Bitmap {
    return getResource(R.mipmap.img_dango)
  }

  fun getItemChocolate(): Bitmap {
    return getResource(R.mipmap.img_choco)
  }

  fun getItemCheese(): Bitmap {
    return getResource(R.mipmap.img_cheese)
  }

  fun getItemStar(): Bitmap {
    return getResource(R.mipmap.img_star)
  }

  fun getSunrise(): Bitmap {
    return getResource(R.mipmap.img_sunrise)
  }

  fun getSun(): Bitmap {
    return getResource(R.mipmap.img_sun)
  }

  fun getCloud(): Bitmap {
    return getResource(R.mipmap.img_cloud)
  }

  fun getMoon(): Bitmap {
    return getResource(R.mipmap.img_moon)
  }

  fun getFivePointStar(): Bitmap {
    return getResource(R.mipmap.img_fpstar)
  }

  private fun getResource(name: Int): Bitmap {
    return BitmapFactory.decodeResource(resource, name)
  }

}