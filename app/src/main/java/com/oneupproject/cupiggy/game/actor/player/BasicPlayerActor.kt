package com.oneupproject.cupiggy.game.actor.player

import android.graphics.Bitmap
import com.oneupproject.cupiggy.game.actor.BasicActor
import com.oneupproject.cupiggy.game.actor.item.BasicItemActor
import com.oneupproject.cupiggy.game.manager.OperationManager
import com.oneupproject.cupiggy.util.Utility
import com.oneupproject.cupiggy.util.Vector
import com.oneupproject.cupiggy.util.helper.ImageHelper

/**
 * BasicPlayer
 *
 * 操作できるActorの基底クラスです。
 *
 * @property life 残機
 * @property isInvincible 無敵モード
 * @property operationVector 方向
 * @author y728n
 * @since 1.0.0
 */

abstract class BasicPlayerActor : BasicActor {

  var life = 1
    private set

  private var isInvincible = false

  /**
   * 状態
   */
  private enum class Status {
    NORMAL,
    SMALL,
    BIG,
  }
  private var isStatus = Status.NORMAL

  protected var operationVector: Vector = Vector()

  init {
    maxSpeed = 20f
  }

  constructor(x: Float, y: Float, image: Bitmap) : super(x, y, image) {
    createCollisionRange(1f, 1f)
  }

  open fun damage() {
    if (isInvincible) {
      return
    }

    life--

    if (life <= 0) {
      destroy()
    }
  }

  override fun calcDirection() {
    operationVector.x = -OperationManager.x
    operationVector.y = OperationManager.y

    operationVector.restrict(maxSpeed)

    vector.blend(OperationManager.vector, 0.1f)
  }

  override fun detain() {
    val right = Utility.getGameWidth() - width
    val bottom = Utility.getGameHeight() - height

    if (x < 0) x = 0.0f
    if (right < x) x = right
    if (y < 0) y = 0.0f
    if (bottom < y) y = bottom
  }

  fun eat(item: BasicItemActor) = when (item.effect) {
    BasicItemActor.Effect.SMALL -> {
      when (isStatus) {
        Status.BIG -> changeNormal()
        else -> changeSmall()
      }
    }
    BasicItemActor.Effect.BIG -> {
      when (isStatus) {
        Status.SMALL -> changeNormal()
        else -> changeBig()
      }
    }
    else -> {
      when (isStatus) {
        Status.BIG -> changeBig()
        else -> changeNormal()
      }
    }
  }

  private fun changeSmall() {
    setGraphic(ImageHelper.getSmallPlayerImage())
    isStatus = Status.SMALL
    maxSpeed = 30f
  }

  private fun changeBig() {
    setGraphic(ImageHelper.getBigPlayerImage())
    isStatus = Status.BIG
    maxSpeed = 3f
  }
  private fun changeNormal() {
    setGraphic(ImageHelper.getPlayerImage())
    isStatus = Status.NORMAL
    maxSpeed = 20f
  }

}