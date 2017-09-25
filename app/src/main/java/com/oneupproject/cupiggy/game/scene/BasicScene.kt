package com.oneupproject.cupiggy.game.scene

import com.oneupproject.cupiggy.game.GameObject

/**
 * BasicScene
 *
 * 各Sceneの基底クラスです。
 *
 * @property isProjecting Scene実行中かどうか
 *
 * @author y728n
 * @since 1.0.0
 */

abstract class BasicScene : GameObject {
  var isProjecting = true
}
