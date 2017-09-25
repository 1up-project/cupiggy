package com.oneupproject.cupiggy.util.helper

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager

/**
 * GyroSensorHelper
 *
 * ジャイロセンサーから受け取った情報を保持します。
 *
 * @property x x軸
 * @property y y軸
 * @property z z軸
 * @author y728n
 * @since 1.0.0
 */

object GyroSensorHelper : BasicHelper, SensorEventListener {

  private var sensorManager: SensorManager? = null

  var x: Float = 0.toFloat()
    private set
  var y: Float = 0.toFloat()
    private set
  var z: Float = 0.toFloat()
    private set

  override fun onCreate(context: Context) {
    sensorManager = context.getSystemService(Context.SENSOR_SERVICE) as SensorManager
    onResume()
  }

  override fun onAccuracyChanged(sensor: Sensor, accuracy: Int) {
    // 何もしない
  }

  /**
   * アクティビティが動き始めたらリスナーを登録する
   */
  override fun onResume() {
    val sensorList = sensorManager?.getSensorList(Sensor.TYPE_ACCELEROMETER)
    if (sensorList != null && !sensorList.isEmpty()) {
      val sensor = sensorList[0]
      sensorManager?.registerListener(this, sensor, SensorManager.SENSOR_DELAY_FASTEST)
    }
  }

  /**
   * アクティビティがポーズになったらリスナーを止める
   */
  override fun onPause() {
    if (sensorManager == null) {
      return
    }
    sensorManager?.unregisterListener(this)
  }

  /**
   * 何もしない
   */
  override fun onDestroy() {
    return
  }

  override fun onSensorChanged(event: SensorEvent) {
    if (event.sensor.type == Sensor.TYPE_ACCELEROMETER) {
      x = event.values[0]
      y = event.values[1]
      z = event.values[2]
    }
  }

}