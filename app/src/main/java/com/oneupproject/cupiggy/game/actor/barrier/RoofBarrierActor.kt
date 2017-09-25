package com.oneupproject.cupiggy.game.actor.barrier

/**
 * RoofBarrier
 *
 * 天井型障害物です。
 * 床型障害物とほぼ同じ仕様なので、FloorBarrierActorを継承させています。
 *
 * @author y728n
 * @since 1.0.0
 */

class RoofBarrierActor : FloorBarrierActor {

  override fun getReferencePointY(): Float {
    return 0f - REFERENCE_Y
  }

  constructor() : super()

}