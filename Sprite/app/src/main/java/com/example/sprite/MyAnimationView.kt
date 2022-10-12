package com.example.sprite


import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import androidx.annotation.Nullable
import java.util.*
import kotlin.collections.ArrayList


class MyAnimationView(context: Context,attributeSet: AttributeSet? = null) : View(context,attributeSet) {
    private var selectedIndex = -1
    private var oldx = 0f
    private var oldy = 0f
    private var screenWidth = 1024
    private var screenHeight = 1024

    init {
        prepareContent()
    }
    private var sprites: ArrayList<My2DSprite>? = null
    private var timer: Timer? = null
    private var timerTask: TimerTask? = null
    private fun prepareContent() {
        sprites = ArrayList()
        createIsland(100, 100, R.drawable.starter_island01)
        createBuilding(50, 150, R.drawable.kindergarten01)
        for (i in 0..4) createAngel(100 + i * 30, 100 + i * 2)
        timerTask = object : TimerTask() {
            override fun run() {
                for (i in 0 until sprites!!.size) sprites!![i].update()
                postInvalidate()
            }
        }
        timer = Timer()
        timer!!.schedule(timerTask, 1000, 40)
    }

    private fun createAngel(left: Int, top: Int) {
        val bmps = arrayOfNulls<Bitmap>(15)

        bmps[0] = BitmapFactory.decodeResource(resources, R.drawable.angel01)
        bmps[1] = BitmapFactory.decodeResource(resources, R.drawable.angel02)
        bmps[2] = BitmapFactory.decodeResource(resources, R.drawable.angel03)
        bmps[3] = BitmapFactory.decodeResource(resources, R.drawable.angel04)
        bmps[4] = BitmapFactory.decodeResource(resources, R.drawable.angel05)
        bmps[5] = BitmapFactory.decodeResource(resources, R.drawable.angel06)
        bmps[6] = BitmapFactory.decodeResource(resources, R.drawable.angel07)
        bmps[7] = BitmapFactory.decodeResource(resources, R.drawable.angel08)
        bmps[8] = BitmapFactory.decodeResource(resources, R.drawable.angel09)
        bmps[9] = BitmapFactory.decodeResource(resources, R.drawable.angel10)
        bmps[10] = BitmapFactory.decodeResource(resources, R.drawable.angel11)
        bmps[11] = BitmapFactory.decodeResource(resources, R.drawable.angel12)
        bmps[12] = BitmapFactory.decodeResource(resources, R.drawable.angel13)
        bmps[13] = BitmapFactory.decodeResource(resources, R.drawable.angel14)
        bmps[14] = BitmapFactory.decodeResource(resources, R.drawable.angel15)

        val newSprite = My2DSprite(bmps.map { it!! }.toTypedArray(), left, top, 0, 0)
        sprites!!.add(newSprite)
    }

    private fun createBuilding(left: Int, top: Int, resID: Int) {
        createSpriteWithASingleImage(left, top, resID)
    }

    private fun createIsland(left: Int, top: Int, resID: Int) {
        createSpriteWithASingleImage(left, top, resID)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        screenWidth = w
        screenHeight = h
    }

    private fun createSpriteWithASingleImage(left: Int, top: Int, resID: Int) {
        val bmps = arrayOfNulls<Bitmap>(1)
        bmps[0] = BitmapFactory.decodeResource(resources, resID)
        val newSprite = My2DSprite(bmps.map { it!! }.toTypedArray(), left, top, 0, 0)
        sprites!!.add(newSprite)
    }

    override fun onDraw(canvas: Canvas) {
        //        super.onDraw(canvas);
        renderAll() // flicker-free
        bmpAll?.let { canvas.drawBitmap(it,0f, 0f, null) }

//        canvas.drawRGB(255, 255, 255);
//        for (int i=0; i<sprites.size(); i++)
//            sprites.get(i).draw(canvas);
    }


    private var bmpAll: Bitmap? = null
    private fun renderAll() {
        bmpAll = Bitmap.createBitmap(screenWidth, screenHeight, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bmpAll!!)
        canvas.drawRGB(255, 255, 255)
        for (i in 0 until sprites!!.size) sprites!![i].draw(canvas)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        val maskedAction = event.actionMasked
        val x = event.x
        val y = event.y
        val tempIdx: Int
        when (maskedAction) {
            MotionEvent.ACTION_DOWN -> {

                // TODO use data
                tempIdx = getSelectedSpriteIndex(x, y)
                /*                if (tempIdx!=-1) {
                    selectSprite(tempIdx);
                    invalidate();
                }*/beginDrag(x, y)
            }
            MotionEvent.ACTION_MOVE -> {
                // a pointer was moved
                // TODO use data
                processDrag(x, y)
            }
            MotionEvent.ACTION_UP -> {
                endDrag(x, y)
            }
        }
        return true
    }

    private fun endDrag(x: Float, y: Float) {
        processDrag(x, y)
        selectedIndex = -1
    }

    private fun processDrag(x: Float, y: Float) {
        val dx = x - oldx
        val dy = y - oldy
        if (selectedIndex != -1) {
            sprites!![selectedIndex].left += dx.toInt()
            sprites!![selectedIndex].top += dy.toInt()
            invalidate()
        }
        oldx = x
        oldy = y
    }

    private fun beginDrag(x: Float, y: Float) {
        val tempIdx = getSelectedSpriteIndex(x, y)
        if (tempIdx != -1) {
            selectedIndex = tempIdx
            oldx = x
            oldy = y
            selectSprite(selectedIndex)
            /*            if (onSpriteClickListener!=null)
            {
                onSpriteClickListener.OnSpriteClick(this, x, y, selectedIndex);
            }*/invalidate()
        } else selectedIndex = -1
    }

    private fun selectSprite(newIdx: Int) {
        for (i in 0 until sprites!!.size) {
            sprites!![i].State = if (newIdx == i) true else false
        }
    }

    private fun getSelectedSpriteIndex(x: Float, y: Float): Int {
        for (i in sprites!!.size - 1 downTo 0) if (sprites!![i].isSelected(x, y)) return i
        return -1
    }
}
