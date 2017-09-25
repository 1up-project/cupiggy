package com.oneupproject.cupiggy.game.actor.item

import com.oneupproject.cupiggy.util.helper.ImageHelper

/**
 * PuddingItem
 *
 * プリンです。2000点。
 *
 * @author y728n
 * @since 1.0.0
 */

class PuddingItemActor : BasicItemActor {

  constructor(x: Float, y: Float) : super(x, y, ImageHelper.getItemPudding()) {
    score = 2000
  }

}