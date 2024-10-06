package com.rprandt.memorygame

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.media.SoundPool
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View

class MemoryGameView(context: Context, attrs: AttributeSet? = null) : View(context, attrs) {

    private val paint = Paint().apply {
        color = Color.BLUE
        style = Paint.Style.FILL
    }

    private var card1FaceUp = false
    private var card2FaceUp = false


    private lateinit var soundPool: SoundPool
    private var cardFlipSound: Int = 0

    fun setSoundPool(soundPool: SoundPool, cardFlipSound: Int) {
        this.soundPool = soundPool
        this.cardFlipSound = cardFlipSound
    }

    private fun playCardFlipSound() {
        soundPool.play(cardFlipSound, 1f, 1f, 1, 0, 1f)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        drawCard(canvas, 100f, 100f, card1FaceUp)
        drawCard(canvas, 300f, 100f, card2FaceUp)
    }

    private fun drawCard(canvas: Canvas, x: Float, y: Float, isFaceUp: Boolean) {
        val width = 150f
        val height = 200f

        paint.color = if (isFaceUp) Color.GREEN else Color.BLUE

        canvas.drawRect(x, y, x + width, y + height, paint)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        if (event.action == MotionEvent.ACTION_DOWN) {
            if (event.x > 100 && event.x < 250 && event.y > 100 && event.y < 300) {
                card1FaceUp = !card1FaceUp
                playCardFlipSound()
            }
            if (event.x > 300 && event.x < 450 && event.y > 100 && event.y < 300) {
                card2FaceUp = !card2FaceUp
                playCardFlipSound()
            }
            invalidate()
        }
        return true
    }
}