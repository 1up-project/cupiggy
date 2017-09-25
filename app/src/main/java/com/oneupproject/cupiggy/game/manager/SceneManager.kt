package com.oneupproject.cupiggy.game

import android.graphics.Canvas
import com.oneupproject.cupiggy.game.scene.BasicScene
import java.util.*

/**
 * SceneManager
 *
 * 各Sceneを管理します。
 *
 * @author y728n
 * @since 1.0.0
 */

object SceneManager : BasicManager() {

  /**
   * 各シーン
   *
   * @property TITLE タイトル画面
   * @property GAME ゲーム画面
   */
  enum class SceneType {
    TITLE,
    GAME,
  }

  private var scenes: LinkedList<BasicScene> = LinkedList()
  private var nowSceneStatus: SceneType = SceneType.TITLE
  private val sceneCreator: SceneCreator = SceneCreator()

  fun changeScene(sceneType: SceneType) {
    scenes.clear()

    nowSceneStatus = sceneType

    when (sceneType) {
      SceneType.TITLE -> scenes.add(sceneCreator.createTitleScene())
      else -> scenes.add(sceneCreator.createGameScene())
    }
  }

  override fun onUpdate(): Boolean {
    scenes.filter { it.onUpdate() }.forEach {
      if (!it.isProjecting) {
        when (nowSceneStatus) {
          SceneType.TITLE -> {
            changeScene(SceneType.GAME)
          }
          else -> {
            changeScene(SceneType.TITLE)
          }
        }
      }
    }

    return true
  }

  override fun onDraw(canvas: Canvas) {
    scenes.forEach { it.onDraw(canvas) }
  }

}