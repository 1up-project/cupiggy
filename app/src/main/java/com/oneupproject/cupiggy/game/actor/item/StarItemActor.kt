package com.oneupproject.cupiggy.game.actor.item

import com.oneupproject.cupiggy.util.helper.ImageHelper

/**
 * StarItem
 *
 * スターです。無敵にはなれませんが、10000点です。
 *
 * @author y728n
 * @since 1.0.0
 */

class StarItemActor : BasicItemActor {

  constructor(x: Float, y: Float) : super(x, y, ImageHelper.getItemStar()) {
    score = 10000
  }

}