package com.oneupproject.cupiggy.game.manager

import android.graphics.Canvas
import com.oneupproject.cupiggy.game.BasicManager
import com.oneupproject.cupiggy.game.actor.item.BasicItemActor
import com.oneupproject.cupiggy.util.Font
import com.oneupproject.cupiggy.util.helper.PreferenceHelper

/**
 * ScoreManager
 *
 * ゲーム中のスコアを管理します。
 *
 * @author y728n
 * @since 1.0.0
 */

object ScoreManager : BasicManager() {

  var score = 0
    private set

  private var isStoppingScoreUp = true

  private val MAX_SCORE = 999999999

  private var count = 0
  private val SCORE_UP_COUNT = 100

  var highscore = PreferenceHelper.readHighscore()
    private set

  private var font = Font()

  private fun getAddedScore(score: Int): Int {
    if (isStoppingScoreUp) {
      return this.score
    }

    var addedScore = this.score + score
    if (addedScore > MAX_SCORE) {
      addedScore = MAX_SCORE
    }
    return addedScore
  }

  fun addScore(score: Int) {
    this.score = getAddedScore(score)
  }

  fun addScore(actor: BasicItemActor) {
    score = getAddedScore(actor.score)
  }

  fun clear() {
    score = 0
    stop()
  }

  fun start() {
    clear()
    isStoppingScoreUp = false
  }

  fun stop() {
    isStoppingScoreUp = true
  }

  override fun onUpdate(): Boolean {
    count++

    if (score > highscore) {
      highscore = score
    }

    if (count > SCORE_UP_COUNT) {
      score = getAddedScore(10)
      count = 0
    }

    return true
  }

  override fun onDraw(canvas: Canvas) {
    font.drawText(String.format("SCORE %1$09d", score), 4f, 4f, canvas)

    val HIGHSCORE_X = 672f
    font.drawText(String.format("HIGHSCORE %1$09d", highscore), HIGHSCORE_X, 4f, canvas)
  }

}