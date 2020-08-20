package com.mrspd.deltaonsiteround3

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;


class CircularView(context: Context?) : View(context) {
    var canvasPaint: Paint
    var circlePaint: Paint
    var currentX = 500f
    var currentY = 500f
    var radius = 170f
    var circleMovable = false
    var circleDragable = false
     override fun onDraw(sceneCanvas: Canvas) {
        super.onDraw(sceneCanvas)
       // sceneCanvas.drawPaint(canvasPaint)
         drawCircle(sceneCanvas)
    }

    private fun drawCircle(sceneCanvas: Canvas) {
        circlePaint.setColor(Color.GREEN)
        sceneCanvas.drawCircle(currentX, currentY, radius, circlePaint)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        val action = event.action
        when (action and MotionEvent.ACTION_MASK) {
            MotionEvent.ACTION_DOWN -> {
                val a = event.getX(0) - currentX
                val b = event.getY(0) - currentY
                if (Math.hypot(a.toDouble(), b.toDouble()) < radius -20) {
                    circleMovable = true
                }
                if (Math.hypot(a.toDouble(), b.toDouble()) <= radius +20  && Math.hypot(a.toDouble(), b.toDouble()) >= radius - 20  ) {
                    circleDragable = true


                }
            }
            MotionEvent.ACTION_UP -> {
                circleMovable = false
                circleDragable = false
            }
            MotionEvent.ACTION_MOVE -> {
                if (circleMovable) {
                    currentX = event.getX(0)
                    currentY = event.getY(0)
                }
                else if (circleDragable){
                    val a = event.getX(0) - currentX
                    val b = event.getY(0) - currentY
                    radius = Math.hypot(a.toDouble(), b.toDouble()).toFloat()
                }
            }
        }
        this.postInvalidate()
        return true
    }

    init {
        canvasPaint = Paint()
        canvasPaint.setStyle(Paint.Style.FILL)
        canvasPaint.setColor(Color.YELLOW)
        circlePaint = Paint()
    }
}