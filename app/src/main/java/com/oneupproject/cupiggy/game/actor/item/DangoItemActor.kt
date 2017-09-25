package com.oneupproject.cupiggy.game.actor.item

import com.oneupproject.cupiggy.util.helper.ImageHelper

/**
 * DangoItem
 *
 * おだんごです。茶団子をイメージしています。
 * 4000点。
 *
 * @author y728n
 * @since 1.0.0
 */

class DangoItemActor : BasicItemActor {

  constructor(x: Float, y: Float) : super(x, y, ImageHelper.getItemDango()) {
    score = 4000
  }

}