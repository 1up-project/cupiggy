package com.oneupproject.cupiggy.util.helper

import android.content.Context
import android.media.MediaPlayer
import com.oneupproject.cupiggy.R

/**
 * MusicHelper
 *
 * BGMを制御します。
 * フェードイン、フェードアウトはできません。
 *
 * @author y728n
 * @since 1.0.0
 */

object MusicHelper : BasicHelper {

  private var resource: Context? = null

  private var leftVolume = 1f
  private var rightVolume = 1f

  private var player = MediaPlayer()

  init {
    player.isLooping = true
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

  private fun setMusic(name: Int) {
    onDestroy()
    player = getResource(name)
    player.isLooping
    play()
  }

  private fun play(channel: Int = 0) {
    if (!player.isPlaying) {
      player.seekTo(channel)
      player.start()
      player.isLooping = true
    }
  }

  fun playVeryEasy() {
    setMusic(R.raw.bgm_veryeasy)
  }

  fun playEasy() {
    setMusic(R.raw.bgm_easy)
  }

  fun playNormal() {
    setMusic(R.raw.bgm_normal)
  }

  fun playHard() {
    setMusic(R.raw.bgm_hard)
  }

  fun playVeryHard() {
    setMusic(R.raw.bgm_veryhard)
  }

}

