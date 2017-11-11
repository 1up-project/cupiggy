package com.oneupproject.cupiggy.game.actor.item

import com.oneupproject.cupiggy.util.helper.ImageHelper

/**
 * CupcakeItem
 *
 * 初代主人公のカップケーキくんです。取ると変身します。
 *
 * @author y728n
 * @since 1.0.0
 */

class CupcakeItemActor : BasicItemActor {

  constructor(x: Float, y: Float) : super(x, y, ImageHelper.getItemCupcake()) {
    score = 1000
    effect = Effect.SMALL
  }

}