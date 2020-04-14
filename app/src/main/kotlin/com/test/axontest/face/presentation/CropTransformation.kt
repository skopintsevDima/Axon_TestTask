package com.test.axontest.face.presentation

import android.graphics.Bitmap
import android.util.Log
import com.squareup.picasso.Transformation
import kotlin.math.min

class CropTransformation(
    private val top: Int,
    private val left: Int,
    private val width: Int,
    private val height: Int,
    private val density: Float
): Transformation {
    override fun transform(source: Bitmap): Bitmap {
        val leftPx = left.toPx()
        val topPx = top.toPx()
        val widthPx = min(width.toPx(), source.width - leftPx)
        val heightPx = min(height.toPx(), source.height - topPx)
        val result = Bitmap.createBitmap(source, leftPx, topPx, widthPx, heightPx)
        if (result != source) {
            source.recycle()
        }
        return result
    }

    override fun key(): String = KEY

    private fun Int.toPx() = (this * density).toInt()

    companion object {
        private const val KEY = "crop"
    }
}