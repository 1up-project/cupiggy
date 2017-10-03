package com.oneupproject.cupiggy.game.actor.barrier

import android.graphics.Color
import com.oneupproject.cupiggy.game.actor.BasicActor
import com.oneupproject.cupiggy.util.Utility

/**
 * BasicBarrier
 *
 * 障害物用の基底クラスです。
 *
 * @author y728n
 * @since 1.0.0
 */

abstract class BasicBarrierActor : BasicActor {

  protected val startPointX = Utility.getGameWidth()
  protected val bottomPoint = Utility.getGameHeight()

  init {
    paint.color = Color.rgb(0, 240, 233)

    minSpeed = 5f
    maxSpeed = 20f

    vector.x = getSpeed()
  }

  constructor()

}