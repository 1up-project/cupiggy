package com.oneupproject.cupiggy.game.actor.item

import com.oneupproject.cupiggy.util.helper.ImageHelper

/**
 * IcePopItem
 *
 * アイスキャンディです。3000点。
 *
 * @author y728n
 * @since 1.0.0
 */

class IcePopItemActor : BasicItemActor {

  constructor(x: Float, y: Float) : super(x, y, ImageHelper.getItemIcePop()) {
    score = 3000
  }

}