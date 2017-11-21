package com.oneupproject.cupiggy.game.actor.item

import android.graphics.Bitmap
import com.oneupproject.cupiggy.game.actor.BasicActor
import com.oneupproject.cupiggy.game.manager.ScoreManager
import com.oneupproject.cupiggy.util.helper.SoundHelper

/**
 * BasicItem
 *
 * Itemの基底クラスです。
 *
 * @author y728n
 * @since 1.0.0
 */

abstract class BasicItemActor : BasicActor {

  var score: Int = 0
    protected set

  init {
    minSpeed = 5f
    maxSpeed = 15f

    vector.x = getSpeed()
  }

  constructor(x: Float, y: Float, image: Bitmap) : super(x, y, image) {
    createCollisionRange(width, height)
  }

  override fun createCollisionRange(width: Float, height: Float) {
    super.createCollisionRange(width * 1.5f, height * 1.5f)
  }

  open fun taken() {
    if (!isAlive) {
      return
    }

    SoundHelper.playTakingItemSound()

    destroy()

    ScoreManager.addScore(score)

  }

}