package com.test.axontest.detector.domain.util

import android.annotation.SuppressLint
import android.media.Image
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy
import org.opencv.core.*
import org.opencv.imgproc.Imgproc
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
    override fun analyze(imageProxy: ImageProxy) {
        if (listener == null) {
            imageProxy.close()
            return
        }

        val frameTimestamp = System.currentTimeMillis()
        imageProxy.image?.let {
            val imgGrayScaled = it.toGrayScale().rotate(imageProxy.imageInfo.rotationDegrees)
            val detectedFaces = detectFaces(imgGrayScaled)
            if (detectedFaces.isNotEmpty()) {
                listener.invoke(DetectedFaceData(detectedFaces[0], frameTimestamp))
            }
        }
        imageProxy.close()
    }

    private fun detectFaces(imageGray: Mat): List<Rect> {
        if (absoluteFaceSize == 0) {
            val height = imageGray.rows()
            if ((height * relativeFaceSize).roundToInt() > 0) {
                absoluteFaceSize = (height * relativeFaceSize).roundToInt()
            }
        }

        val faces = MatOfRect()
        faceDetector.detectMultiScale(
            imageGray,
            faces,
            SCALE_FACTOR,
            MIN_NEIGHBORS,
            FLAGS,
            absoluteFaceSize.toDouble().let { Size(it, it) },
            Size()
        )
        return faces.toList()
    }

    companion object {
        private const val SCALE_FACTOR = 1.1
        private const val MIN_NEIGHBORS = 2
        private const val FLAGS = 2

        private fun Image.toGrayScale(): Mat {
            assert(planes[0].pixelStride == 1)
            val yPlane = planes[0].buffer
            val yPlaneStep = planes[0].rowStride
            return Mat(height, width, CvType.CV_8UC1, yPlane, yPlaneStep.toLong())
                .also { Imgproc.equalizeHist(it, it) }
        }

        private fun Mat.rotate(angle: Int): Mat {
            when (angle) {
                0 -> Unit
                90 -> { Core.rotate(this, this, Core.ROTATE_90_CLOCKWISE) }
                180 -> { Core.rotate(this, this, Core.ROTATE_180) }
                270 -> { Core.rotate(this, this, Core.ROTATE_90_COUNTERCLOCKWISE) }
                else -> Unit
            }
            return this
        }
    }
}