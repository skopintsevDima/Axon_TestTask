package com.test.axontest.face.presentation

import android.graphics.Bitmap
import com.squareup.picasso.Transformation

class CropTransformation(
    private val top: Int,
    private val left: Int,
    private val width: Int,
    private val height: Int
): Transformation {
    override fun transform(source: Bitmap): Bitmap {
        return try {
            Bitmap.createBitmap(source, left, top, width, height)
                .also { if (it != source) source.recycle() }
        } catch (e: Exception) {
            e.printStackTrace()
            source
        }
    }

    override fun key(): String = KEY

    companion object {
        private const val KEY = "crop"
    }
}