package com.oneupproject.cupiggy.util.helper

import android.content.Context
import android.media.MediaPlayer
import com.oneupproject.cupiggy.R

/**
 * SoundHelper
 *
 * 効果音情報をMediaPlayerで返します。
 * API別の制御が必要なため、SoundPoolは未使用です。
 *
 * @author y728n
 * @since 1.0.0
 */

object SoundHelper : BasicHelper {

  private var resource: Context? = null

  private var leftVolume = 1f
  private var rightVolume = 1f

  private var player = MediaPlayer()

  init {
    player.isLooping = false
    player.setVolume(leftVolume, rightVolume)
  }

  override fun onCreate(context: Context) {
    resource = context
  }

  override fun onResume() {
    return
  }

  override fun onPause() {
    if (player.isPlaying) {
      player.pause()
    }
  }

  override fun onDestroy() {
    player.release()
  }

  private fun getResource(name: Int): MediaPlayer {
    return MediaPlayer.create(resource, name)
  }

  private fun start(name: Int) {
    onDestroy()
    player = getResource(name)
    play()
  }

  private fun play(channel: Int = 0) {
    if (!player.isPlaying) {
      player.seekTo(channel)
      player.start()
      player.isLooping = false
    }
  }

  fun playTakingItemSound() {
    start(R.raw.se_get_item)
  }

  fun playGameOver() {
    start(R.raw.se_gameover)
  }

}