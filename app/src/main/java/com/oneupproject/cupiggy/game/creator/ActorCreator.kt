package com.oneupproject.cupiggy.game

import com.oneupproject.cupiggy.game.actor.barrier.*
import com.oneupproject.cupiggy.game.actor.player.PlayerActor
import com.oneupproject.cupiggy.game.actor.item.*
import com.oneupproject.cupiggy.game.actor.score.ScoreActor
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
    val x = Utility.getGameWidth()
    val y = Utility.getRandomFloatValue(Utility.getGameHeight().toInt())
    val itemNum = Utility.getRandomIntValue(100)

    return when {
      itemNum < 30 -> {
        createStrawberry(x, y)
      }
      itemNum < 55 -> {
        createPudding(x, y)
      }
      itemNum < 70 -> {
        createDango(x, y)
      }
      itemNum < 82 -> {
        createIcePop(x, y)
      }
      itemNum < 90 -> {
        createChocolate(x, y)
      }
      itemNum < 97 -> {
        createCheese(x, y)
      }
      else -> {
        createStar(x, y)
      }
    }
  }

  fun createWall() = WallBarrierActor()
  fun createFloatingWall() = FloatingWallBarrierActor()
  fun createMovingBarrier() = MovingBarrierActor()
  fun createFloor() = FloorBarrierActor()
  fun createRoof() = RoofBarrierActor()

  fun createScore(x: Float, y: Float, score:Int) = ScoreActor(x, y, score)

}
