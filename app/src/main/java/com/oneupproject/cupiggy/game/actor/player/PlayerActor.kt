package com.oneupproject.cupiggy.game.actor.player

import com.oneupproject.cupiggy.util.helper.ImageHelper

/**
 * Player
 *
 * 操作できるActorです。BasicPlayerActorを継承します。
 * 主機能はほとんどBasicPlayerが持っています。
 *
 * @author y728n
 * @since 1.0.0
 */

class PlayerActor : BasicPlayerActor {

  constructor(x: Float, y: Float) : super(x, y, ImageHelper.getPlayerImage())

  constructor(player: BasicPlayerActor) : super(player.x, player.y, ImageHelper.getPlayerImage())

}