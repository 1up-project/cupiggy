package com.oneupproject.cupiggy.game.actor.item

import com.oneupproject.cupiggy.util.helper.ImageHelper

/**
 * ChocolateItem
 *
 * チョコレートです。5000点。
 *
 * @author y728n
 * @since 1.0.0
 */

class ChocolateItemActor : BasicItemActor {

  constructor(x: Float, y: Float) : super(x, y, ImageHelper.getItemChocolate()) {
    score = 5000
  }

}