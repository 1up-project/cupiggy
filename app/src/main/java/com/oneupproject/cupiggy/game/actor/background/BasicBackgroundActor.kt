package com.oneupproject.cupiggy.game.actor.background

import com.oneupproject.cupiggy.game.actor.BasicActor
import com.oneupproject.cupiggy.util.Utility

/**
 * BasicBackground
 *
 * Backgroundの基底クラスです。
 *
 * @author y728n
 * @since 1.0.0
 */

abstract class BasicBackgroundActor : BasicActor {

  constructor() : super(0f, 0f)

  init {
    width = Utility.getGameWidth()
    height = Utility.getGameHeight()
    paint.isAntiAlias = true
  }

}