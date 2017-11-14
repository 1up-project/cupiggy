package com.oneupproject.cupiggy.game.actor.item

import com.oneupproject.cupiggy.util.helper.ImageHelper

/**
 * BigCupcakeItem
 *
 * 初代主人公のカップケーキくんです。取ると変身します。
 *
 * @author y728n
 * @since 1.0.0
 */

class BigCupcakeItemActor : BasicItemActor {

  constructor(x: Float, y: Float) : super(x, y, ImageHelper.getItemBigCupcake()) {
    score = 1000
    effect = Effect.BIG
  }

}