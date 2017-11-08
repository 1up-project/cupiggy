package com.oneupproject.cupiggy.game.actor.score

import android.graphics.Bitmap
import android.graphics.Canvas
import com.oneupproject.cupiggy.game.actor.BasicActor
import com.oneupproject.cupiggy.game.manager.ScoreManager
import com.oneupproject.cupiggy.util.Font
import com.oneupproject.cupiggy.util.helper.SoundHelper

/**
 * Created by 0098 on 2017/10/27.
 */
abstract class BasicScoreActor : BasicActor {

    var score: Int = 0
        protected set

    private var font = Font()

    init {
        minSpeed = 5f
        maxSpeed = 15f

        vector.x = -3f
    }

    constructor(x: Float, y: Float, score: Int) : super(x, y) {
        this.score = score
    }

    override fun onDraw(canvas: Canvas) {
        font.drawText(score.toString(), x, y, canvas)
    }
}