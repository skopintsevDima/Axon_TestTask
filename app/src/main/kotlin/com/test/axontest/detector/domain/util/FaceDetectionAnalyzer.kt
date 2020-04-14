package com.test.axontest.detector.domain.util

import android.annotation.SuppressLint
import android.media.Image
import android.util.Log
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy
import org.opencv.core.*
import org.opencv.objdetect.CascadeClassifier
import kotlin.math.roundToInt

data class DetectedFaceData(
    val face: Rect,
    val timestamp: Long
)
typealias FaceDetectionListener = (detectedFaceData: DetectedFaceData?) -> Unit

class FaceDetectionAnalyzer(
    private val faceDetector: CascadeClassifier,
    private val listener: FaceDetectionListener? = null
) : ImageAnalysis.Analyzer {
    private val relativeFaceSize = 0.2f
    private var absoluteFaceSize = 0

    @SuppressLint("UnsafeExperimentalUsageError")
    override fun analyze(image: ImageProxy) {
        if (listener == null) {
            image.close()
            return
        }

        val frameTimestamp = System.currentTimeMillis()
        image.image?.let {
            val detectedFaces = detectFaces(it)
            if (detectedFaces.isNotEmpty()) {
                listener.invoke(DetectedFaceData(detectedFaces[0], frameTimestamp))
            }
        }
        image.close()
    }

    private fun detectFaces(image: Image): List<Rect> {
        val imgGrayScaled = image.toGrayScale()

        if (absoluteFaceSize == 0) {
            val height = imgGrayScaled.rows()
            if ((height * relativeFaceSize).roundToInt() > 0) {
                absoluteFaceSize = (height * relativeFaceSize).roundToInt()
            }
        }

        val faces = MatOfRect()
        faceDetector.detectMultiScale(
            imgGrayScaled,
            faces,
            SCALE_FACTOR,
            MIN_NEIGHBORS,
            FLAGS,
            absoluteFaceSize.toDouble().let { Size(it, it) },
            Size()
        )
        return faces.toList()
    }

    private fun Image.toGrayScale(): Mat {
        assert(planes[0].pixelStride == 1)
        val yPlane = planes[0].buffer
        val yPlaneStep = planes[0].rowStride
        return Mat(height, width, CvType.CV_8UC1, yPlane, yPlaneStep.toLong())
    }

    companion object {
        const val SCALE_FACTOR = 1.1
        const val MIN_NEIGHBORS = 2
        const val FLAGS = 2
    }
}