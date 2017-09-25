package com.oneupproject.cupiggy.game

import com.oneupproject.cupiggy.game.actor.barrier.*
import com.oneupproject.cupiggy.game.actor.player.PlayerActor
import com.oneupproject.cupiggy.game.actor.item.*
import com.oneupproject.cupiggy.util.Utility

/**
 * ActorCreator
 *
 * Actorを生成します。
 *
 * @author y728n
 * @since 1.0.0
 */

class ActorCreator : BasicCreator() {

  fun createPlayer(x: Float, y: Float) = PlayerActor(x, y)

  private fun createStrawberry(x: Float, y: Float) = StrawberryItemActor(x, y)
  private fun createPudding(x: Float, y: Float) = PuddingItemActor(x, y)
  private fun createCheese(x: Float, y: Float) = CheeseItemActor(x, y)
  private fun createChocolate(x: Float, y: Float) = ChocolateItemActor(x, y)
  private fun createDango(x: Float, y: Float) = DangoItemActor(x, y)
  private fun createIcePop(x: Float, y: Float) = IcePopItemActor(x, y)
  private fun createStar(x: Float, y: Float) = StarItemActor(x, y)

  fun createRandomItem(): BasicItemActor {
    val X = Utility.getGameWidth()
    val Y = Utility.getRandomFloatValue(Utility.getGameHeight().toInt())
    val ITEM_NUM = Utility.getRandomIntValue(100)

    return when {
      ITEM_NUM < 30 -> {
        createStrawberry(X, Y)
      }
      ITEM_NUM < 55 -> {
        createPudding(X, Y)
      }
      ITEM_NUM < 70 -> {
        createDango(X, Y)
      }
      ITEM_NUM < 82 -> {
        createIcePop(X, Y)
      }
      ITEM_NUM < 90 -> {
        createChocolate(X, Y)
      }
      ITEM_NUM < 97 -> {
        createCheese(X, Y)
      }
      else -> {
        createStar(X, Y)
      }
    }
  }

  fun createWall() = WallBarrierActor()
  fun createFloatingWall() = FloatingWallBarrierActor()
  fun createMovingBarrier() = MovingBarrierActor()
  fun createFloor() = FloorBarrierActor()
  fun createRoof() = RoofBarrierActor()

}
