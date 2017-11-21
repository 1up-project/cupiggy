package com.oneupproject.cupiggy.game.scene

import android.graphics.Canvas
import com.oneupproject.cupiggy.game.ActorCreator
import com.oneupproject.cupiggy.game.actor.background.*
import com.oneupproject.cupiggy.game.actor.barrier.BasicBarrierActor
import com.oneupproject.cupiggy.game.actor.player.BasicPlayerActor
import com.oneupproject.cupiggy.game.actor.item.BasicItemActor
import com.oneupproject.cupiggy.game.actor.score.BasicScoreActor
import com.oneupproject.cupiggy.game.manager.GameManager
import com.oneupproject.cupiggy.game.manager.OperationManager
import com.oneupproject.cupiggy.game.manager.ScoreManager
import com.oneupproject.cupiggy.util.Font
import com.oneupproject.cupiggy.util.Utility
import com.oneupproject.cupiggy.util.helper.MusicHelper
import com.oneupproject.cupiggy.util.helper.PreferenceHelper
import com.oneupproject.cupiggy.util.helper.SoundHelper
import java.util.*

/**
 * GameScene
 *
 * ゲーム画面です。
 *
 * @author y728n
 * @since 1.0.0
 */

class GameScene : BasicScene {

  private val actorCreator: ActorCreator = ActorCreator()

  private var backgroundActors: LinkedList<BasicBackgroundActor> = LinkedList()
  private var playerActors: LinkedList<BasicPlayerActor> = LinkedList()
  private var itemActors: LinkedList<BasicItemActor> = LinkedList()
  private var barrierActors: LinkedList<BasicBarrierActor> = LinkedList()
  private var scoreActors: LinkedList<BasicScoreActor> = LinkedList()

  private var count = 0
  private var maxCount = 10000

  private var nowDifficulty = GameManager.Difficulty.NONE

  private var isGameOver = false
  private var font = Font()

  constructor() : super() {
    ScoreManager.start()

    OperationManager.clearInputState()

    backgroundActors.add(SunriseBackgroundActor())
    playerActors.add(actorCreator.createPlayer(100.0f, 100.0f))

    changeDifficulty()
  }

  private fun createItem() {
    if ((count % 30) != 0) {
      return
    }

    itemActors.add(actorCreator.createRandomItem())
  }

  private fun createBarrier() {
    if ((count % 50) == 0) {
      barrierActors.add(actorCreator.createFloor())
      barrierActors.add(actorCreator.createRoof())
    }
    if ((count % 100) == 0) {
      if (GameManager.Difficulty.EASY.gradePoint <= nowDifficulty.gradePoint) {
        barrierActors.add(actorCreator.createWall())
      }
    }
    if ((count % 150) == 0) {
      if (GameManager.Difficulty.NORMAL.gradePoint == nowDifficulty.gradePoint ||
          GameManager.Difficulty.VERY_HARD.gradePoint <= nowDifficulty.gradePoint) {
        barrierActors.add(actorCreator.createMovingBarrier())
      }
    }
    if ((count % 130) == 0) {
      if (GameManager.Difficulty.HARD.gradePoint <= nowDifficulty.gradePoint) {
        barrierActors.add(actorCreator.createFloatingWall())
      }
    }
  }

  private fun changeDifficulty() {
    val DIFFICULTY = Utility.getNowDifficulty()

    if (nowDifficulty == DIFFICULTY) {
      return
    }

    nowDifficulty = DIFFICULTY
    backgroundActors.forEach { it.destroy() }

    when (nowDifficulty) {
      GameManager.Difficulty.VERY_EASY -> {
        MusicHelper.playVeryEasy()
        backgroundActors.add(SunriseBackgroundActor())
      }
      GameManager.Difficulty.EASY -> {
        MusicHelper.playEasy()
        backgroundActors.add(SunnyBackgroundActor())
      }
      GameManager.Difficulty.NORMAL -> {
        MusicHelper.playNormal()
        backgroundActors.add(CloudyBackgroundActor())
      }
      GameManager.Difficulty.HARD -> {
        MusicHelper.playHard()
        backgroundActors.add(MidnightBackgroundActor())
      }
      GameManager.Difficulty.VERY_HARD -> {
        MusicHelper.playVeryHard()
        backgroundActors.add(UniverseBackgroundActor())
      }
      else -> {
        MusicHelper.playVeryEasy()
      }
    }
  }

  private fun gameOver() {
    isGameOver = true

    ScoreManager.stop()
    MusicHelper.onPause()
    SoundHelper.playGameOver()
    PreferenceHelper.updateHighscore(ScoreManager.highscore)
  }

  override fun onUpdate(): Boolean {
    count++

    createBarrier()
    createItem()
    changeDifficulty()

    if (count > maxCount) {
      count = 0
    }

    backgroundActors.filter { it.onUpdate() }


    playerActors.filter { it.onUpdate() }
        .forEach {
          itemActors.forEach { actor ->
            if (it.hit(actor)) {
              actor.taken()
            }
          }
          barrierActors.forEach { actor ->
            if (it.hit(actor)) {
              it.damage()
            }
          }
        }

    itemActors.filter { it.onUpdate() }
        .forEach {
          playerActors.forEach { actor ->
            if (it.hit(actor)) {
              it.taken()
              scoreActors.add(actorCreator.createScore(actor.x, actor.y , it.score))
            }
          }
        }

    barrierActors.filter { it.onUpdate() }
        .forEach {
          playerActors.forEach { actor ->
            if (it.hit(actor)) {
              actor.damage()
            }
          }
        }

    scoreActors.filter { it.onUpdate() }

    backgroundActors.removeAll { !it.isAlive }
    playerActors.removeAll { !it.isAlive }
    itemActors.removeAll { !it.isAlive }
    barrierActors.removeAll { !it.isAlive }
    scoreActors.removeAll { !it.isAlive }

    if (playerActors.isEmpty() && !isGameOver) {
      gameOver()
    }

    if (isGameOver && OperationManager.isTouching()) {
      isProjecting = false
    }

    return true
  }

  override fun onDraw(canvas: Canvas) {
    backgroundActors.filter { it.isVisible }.forEach { it.onDraw(canvas) }
    playerActors.filter { it.isVisible }.forEach { it.onDraw(canvas) }
    itemActors.filter { it.isVisible }.forEach { it.onDraw(canvas) }
    barrierActors.filter { it.isVisible }.forEach { it.onDraw(canvas) }
    scoreActors.filter { it.isVisible }.forEach { it.onDraw(canvas)}

    if (isGameOver) {
      font.drawCenteringText("GAME OVER", 500f, canvas)
    }
  }

}