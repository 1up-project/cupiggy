package com.oneupproject.cupiggy.game.actor.item

import com.oneupproject.cupiggy.util.helper.ImageHelper

/**
 * StrawberryItem
 *
 * いちごです。1000点。
 *
 * @author y728n
 * @since 1.0.0
 */

class StrawberryItemActor : BasicItemActor {

  constructor(x: Float, y: Float) : super(x, y, ImageHelper.getItemStrawberry()) {
    score = 1000
  }

}