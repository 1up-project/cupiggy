package com.oneupproject.cupiggy.game.actor

import android.graphics.*
import com.oneupproject.cupiggy.game.GameObject
import com.oneupproject.cupiggy.util.Utility
import com.oneupproject.cupiggy.util.Vector

/**
 * BasicActor
 *
 * 各Actorの基底クラスです。
 * 座標や当たり判定など、ほとんどの機能を持っています。
 *
 * @property x 位置（x軸）
 * @property y 位置（y軸）
 * @property width サイズ（横）
 * @property height サイズ（縦）
 * @property vector 方向
 * @property minSpeed 最小速度
 * @property maxSpeed 最大速度
 * @property collisionRangeWidth 当たり判定横幅
 * @property collisionRangeHeight 当たり判定縦幅
 * @property collisionRange 当たり判定
 * @property baseImage 画像
 * @property paint Paintクラス
 * @property isAlive アクターの生存状態
 * @property isVisible 表示状態
 *
 * @author y728n
 * @since 1.0.0
 */

abstract class BasicActor : GameObject {

  var x = 0f
    protected set
  var y = 0f
    protected set
  var width = 0f
    protected set
  var height = 0f
    protected set

  var vector = Vector()
    protected set
  var minSpeed = 0f
    protected set
  var maxSpeed = 0f
    protected set

  var collisionRangeWidth = 0f
    protected set
  var collisionRangeHeight = 0f
    protected set

  var collisionRange: RectF? = null // 当たり判定
    protected set

  var baseImage: Bitmap? = null
    protected set
  var paint = Paint()
    private set

  var isAlive: Boolean = true
    private set
  var isVisible: Boolean = true
    private set

  init {
    paint.isAntiAlias = true
  }

  constructor()

  constructor(x: Float, y: Float) : super() {
    this.x = x
    this.y = y
  }

  constructor(x: Float, y: Float, image: Bitmap) : this(x, y) {
    setGraphic(image)
  }

  /**
   * スピードを設定します。
   *
   * @return 取得したスピードを返す。（右か登場するため、常にマイナス値）
   */
  protected open fun getSpeed(): Float {
    val speed = Utility.getRandomFloatValue(maxSpeed.toInt())

    if (speed < minSpeed) {
      return -minSpeed
    }

    return -speed
  }

  /**
   * 衝突判定用の矩形を設定します。
   *
   * @param width 横幅
   * @param height 縦幅
   */
  protected open fun createCollisionRange(width: Float, height: Float) {
    this.collisionRangeWidth = width
    this.collisionRangeHeight = height

    updateCollisionPoint()
  }

  /**
   * 衝突判定の矩形座標を更新します。
   * 見た目のサイズと座標と異なる当たり判定を設定可能です。
   */
  private fun updateCollisionPoint() {
    if (collisionRange == null) {
      collisionRange = RectF()
    }

    val centerPointX = (x + width) - (width / 2)
    val centerPointY = (y + height) - (height / 2)

    val left = centerPointX - (collisionRangeWidth / 2)
    val top = centerPointY - (collisionRangeHeight / 2)
    val right = left + collisionRangeWidth
    val bottom = top + collisionRangeHeight

    collisionRange?.left = left
    collisionRange?.top = top
    collisionRange?.right = right
    collisionRange?.bottom = bottom
  }

  /**
   * 当たり判定
   *
   * @param otherActor 判定したいActorクラス
   * @return 矩形が重なっている場合、Trueを返す
   */
  open fun hit(otherActor: BasicActor): Boolean {
    if (this.collisionRange == null || otherActor.collisionRange == null) {
      return false
    }

    var rect: RectF = this.collisionRange!!
    return rect.intersect(otherActor.collisionRange)
  }

  /**
   * 対象のActorを消滅対象に変更します。
   */
  fun destroy() {
    isAlive = false
  }

  /**
   * 画像を指定し、画像ファイルからActorの横幅と縦幅を設定します。
   *
   * @param image 表示したい画像
   */
  protected fun setGraphic(image: Bitmap) {
    baseImage = image

    width = baseImage!!.width.toFloat()
    height = baseImage!!.height.toFloat()
  }

  /**
   * 画像をCanvasに設定します。
   *
   * @param canvas 画像の描画先のCanvas
   */
  private fun drawGraphic(canvas: Canvas) {
    canvas.drawBitmap(baseImage, x, y, paint)
  }

  /**
   * Actorが画面外の遠くに出て行ったかどうかを判定します。
   *
   * @return 画面外＋200でTrueを返します。
   */
  private fun beOut(): Boolean {
    val widhtExtra = 200
    val heightExtra = 200

    val leftExtra = -1 * widhtExtra
    val topExtra = -1 * heightExtra
    val rightExtra = Utility.getGameWidth() + widhtExtra
    val bottomExtra = Utility.getGameHeight() + heightExtra

    return (x < leftExtra) || (y < topExtra) || (x > rightExtra) || (y > bottomExtra)
  }

  /**
   * 方向転換
   */
  protected open fun calcDirection() {}

  /**
   * 移動処理
   */
  protected open fun move() {
    x += vector.x
    y += vector.y
  }

  /**
   * 引き止め処理
   * 一定範囲を超える移動を停止させます。
   */
  protected open fun detain() {}

  /**
   * 更新処理
   *
   * 当たり判定を設定している場合、座標を更新。
   * 画面外に出て行った場合は自滅。
   * 方向転換 -> 移動 -> 引き止め処理
   * ……と処理します。
   *
   * @return 常にTrue
   */
  override fun onUpdate(): Boolean {
    if (collisionRange != null) {
      updateCollisionPoint()
    }

    if (beOut()) {
      destroy()
    }

    calcDirection()
    move()
    detain()

    return true
  }

  /**
   * 描画処理
   *
   * Actorは画像がある前提なので、基本的にはBitmapを描画します。
   *
   * @param canvas 描画用Canvas
   */
  override fun onDraw(canvas: Canvas) {
    drawGraphic(canvas)
  }

}