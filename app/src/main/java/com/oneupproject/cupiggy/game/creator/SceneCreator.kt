package com.oneupproject.cupiggy.game

import com.oneupproject.cupiggy.game.scene.GameScene
import com.oneupproject.cupiggy.game.scene.TitleScene


/**
 * SceneCreator
 *
 * Sceneを生成します。
 *
 * @author y728n
 * @since 1.0.0
 */

class SceneCreator : BasicCreator() {

  fun createTitleScene() = TitleScene()
  fun createGameScene() = GameScene()

}