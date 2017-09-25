package com.oneupproject.cupiggy.game.actor.item

import com.oneupproject.cupiggy.util.helper.ImageHelper

/**
 * CheeseItem
 *
 * チーズです。6000点。
 *
 * @author y728n
 * @since 1.0.0
 */

class CheeseItemActor : BasicItemActor {

  constructor(x: Float, y: Float) : super(x, y, ImageHelper.getItemCheese()) {
    score = 6000
  }

}