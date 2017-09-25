package com.oneupproject.cupiggy.game.scene

import android.graphics.Canvas
import com.oneupproject.cupiggy.game.ActorCreator
import com.oneupproject.cupiggy.game.actor.item.BasicItemActor
import com.oneupproject.cupiggy.game.manager.OperationManager
import com.oneupproject.cupiggy.game.manager.ScoreManager
import com.oneupproject.cupiggy.util.Font
import java.util.*

/**
 * TitleScene
 *
 * タイトル画面です。
 *
 * @author y728n
 * @since 1.0.0
 */

class TitleScene : BasicScene {

  private var font = Font()

  private val actorCreator: ActorCreator = ActorCreator()

  private var itemActors: LinkedList<BasicItemActor> = LinkedList()

  private var count = 0

  constructor() : super() {
    ScoreManager.clear()
    OperationManager.clearInputState()
  }

  private fun createItem() {
    if ((count % 30) != 0) {
      return
    }

    itemActors.add(actorCreator.createRandomItem())
    count = 0
  }

  override fun onUpdate(): Boolean {
    createItem()

    itemActors.filter { it.onUpdate() }

    itemActors.removeAll { !it.isAlive }

    if (OperationManager.isTouching()) {
      isProjecting = false
    }

    return true
  }

  override fun onDraw(canvas: Canvas) {
    itemActors.filter { it.isVisible }.forEach { it.onDraw(canvas) }

    font.drawCenteringText("TOUCH THE SCREEN", 500f, canvas)
  }

}