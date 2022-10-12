package com.example.sprite

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Rect


class My2DSprite(bmps: Array<Bitmap>, left: Int, top: Int, width: Int, height: Int) {
     var nBMPs: Int
     var iBMP: Int
     var BMPs: Array<Bitmap>
     var left: Int
     var top: Int
     var width: Int
     var height: Int
     var State = false
     var d1 = 0f
     var d2 = 1f
    fun update() {
        iBMP = (iBMP + 1) % nBMPs
        if (State) {
            d1 += d2
            if (Math.abs(d1) >= 10) d2 *= -1f
        }
    }

    fun draw(canvas: Canvas) {
        if (State) {
            val source: Rect = Rect(0, 0, width - 1, height - 1)
            val target: Rect = Rect((left - d1).toInt(),
                (top - d1).toInt(),
                (left + width - 1 + d1).toInt(),
                (top + height - 1 + d1).toInt())
            canvas.drawBitmap(BMPs[iBMP], source, target, null)
        } else canvas.drawBitmap(BMPs[iBMP], left.toFloat(), top.toFloat(), null)
    }

    fun isSelected(x: Float, y: Float): Boolean {
        return x >= left && x < left + width && y >= top && y < top + height
    }

    init {
        var width = width
        var height = height
        BMPs = bmps
        nBMPs = bmps.size
        iBMP = 0
        this.left = left
        this.top = top
        if (width == 0 && height == 0) {
            width = bmps[0].width
            height = bmps[0].height
        }
        this.width = width
        this.height = height
    }
}
