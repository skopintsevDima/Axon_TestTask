package com.test.axontest.detector.presentation

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.hardware.display.DisplayManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log
import androidx.camera.core.*
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.test.axontest.R
import com.test.axontest.app.App
import com.test.axontest.detector.domain.util.DetectedFaceData
import com.test.axontest.detector.domain.util.FaceDetectionAnalyzer
import kotlinx.android.synthetic.main.activity_detector.*
import org.opencv.objdetect.CascadeClassifier
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import javax.inject.Inject
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min

class DetectorActivity : AppCompatActivity() {
    @Inject
    lateinit var faceDetector: CascadeClassifier
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: DetectorViewModel
    private lateinit var cameraExecutor: ExecutorService
    private var lensFacing: Int = CameraSelector.LENS_FACING_FRONT
    private var preview: Preview? = null
    private var imageCapture: ImageCapture? = null
    private var imageAnalyzer: ImageAnalysis? = null
    private var camera: Camera? = null
    private var displayId: Int = -1

    private val displayManager by lazy {
        getSystemService(Context.DISPLAY_SERVICE) as DisplayManager
    }

    private val displayListener = object : DisplayManager.DisplayListener {
        override fun onDisplayAdded(displayId: Int) = Unit
        override fun onDisplayRemoved(displayId: Int) = Unit
        override fun onDisplayChanged(displayId: Int) = rootLayout?.let { view ->
            if (displayId == this@DetectorActivity.displayId) {
                Log.d(TAG, "Rotation changed: ${view.display.rotation}")
                imageCapture?.targetRotation = view.display.rotation
                imageAnalyzer?.targetRotation = view.display.rotation
            }
        } ?: Unit
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detector)
        init()
    }

    private fun init() {
        App.appComponent.activityComponent().create().inject(this)
        viewModel = viewModelFactory.create(DetectorViewModel::class.java)
        cameraExecutor = Executors.newSingleThreadExecutor()

        // Every time the orientation of device changes, update rotation for use cases
        displayManager.registerDisplayListener(displayListener, null)

        // Wait for the views to be properly laid out
        previewView.post {
            displayId = previewView.display.displayId
            bindCameraUseCases()
        }
    }

    private fun bindCameraUseCases() {
        val metrics = DisplayMetrics().also { previewView.display.getRealMetrics(it) }
        Log.d(TAG, "Screen metrics: ${metrics.widthPixels} x ${metrics.heightPixels}")

        val screenAspectRatio = aspectRatio(metrics.widthPixels, metrics.heightPixels)
        Log.d(TAG, "Preview aspect ratio: $screenAspectRatio")

        val rotation = previewView.display.rotation

        // Bind the CameraProvider to the LifeCycleOwner
        val cameraSelector = CameraSelector.Builder().requireLensFacing(lensFacing).build()
        val cameraProviderFuture = ProcessCameraProvider.getInstance(this)
        cameraProviderFuture.addListener(Runnable {

            val cameraProvider: ProcessCameraProvider = cameraProviderFuture.get()

            preview = Preview.Builder()
                .setTargetAspectRatio(screenAspectRatio)
                .setTargetRotation(rotation)
                .build()

            imageCapture = ImageCapture.Builder()
                .setCaptureMode(ImageCapture.CAPTURE_MODE_MINIMIZE_LATENCY)
                .setTargetAspectRatio(screenAspectRatio)
                .setTargetRotation(rotation)
                .build()

            imageAnalyzer = ImageAnalysis.Builder()
                .setTargetAspectRatio(screenAspectRatio)
                .setTargetRotation(rotation)
                .build()
                .also {
                    it.setAnalyzer(cameraExecutor, FaceDetectionAnalyzer(faceDetector) { data ->
                        data?.let {
                            Log.d(TAG, "Face detected: ${data.face}, timestamp = ${data.timestamp}")
                            runOnUiThread {
                                imageAnalyzer?.clearAnalyzer()
                                cameraProvider.unbind(preview, imageAnalyzer)
                            }
                            takeFacePhoto(data)
                        }
                    })
                }

            cameraProvider.unbindAll()

            try {
                camera = cameraProvider.bindToLifecycle(
                    this,
                    cameraSelector,
                    preview,
                    imageCapture,
                    imageAnalyzer
                )

                preview?.setSurfaceProvider(previewView.createSurfaceProvider(camera?.cameraInfo))
            } catch(exc: Exception) {
                Log.e(TAG, "Use case binding failed", exc)
            }

        }, ContextCompat.getMainExecutor(this))
    }

    private fun aspectRatio(width: Int, height: Int): Int {
        val previewRatio = max(width, height).toDouble() / min(width, height)
        if (abs(previewRatio - RATIO_4_3_VALUE) <= abs(previewRatio - RATIO_16_9_VALUE)) {
            return AspectRatio.RATIO_4_3
        }
        return AspectRatio.RATIO_16_9
    }

    private fun takeFacePhoto(detectedFaceData: DetectedFaceData) {
        imageCapture?.run {
            takePicture(
                viewModel.buildPhotoFileOutputOptions(detectedFaceData.timestamp),
                cameraExecutor,
                object : ImageCapture.OnImageSavedCallback {
                    override fun onError(exc: ImageCaptureException) {
                        Log.e(TAG, "Photo capture failed: ${exc.message}", exc)
                    }

                    override fun onImageSaved(output: ImageCapture.OutputFileResults) {
                        runOnUiThread {
                            viewModel.saveDetectedFace(detectedFaceData, output).observe(
                                this@DetectorActivity,
                                Observer { handleSaveFaceResult(it) }
                            )
                        }
                    }
                }
            )
        }
    }

    private fun handleSaveFaceResult(savedDetectedFaceRes: Result<Long>) {
        savedDetectedFaceRes.fold(
            {
                val resultData = Intent().apply { putExtra(DETECTED_FACE_ID, it) }
                setResult(RESULT_OK, resultData)
            },
            { setResult(Activity.RESULT_CANCELED) }
        )
        finish()
    }

    override fun onDestroy() {
        super.onDestroy()
        cameraExecutor.shutdown()
        displayManager.unregisterDisplayListener(displayListener)
    }

    companion object {
        private const val TAG = "DetectorXActivity"
        private const val RATIO_4_3_VALUE = 4.0 / 3.0
        private const val RATIO_16_9_VALUE = 16.0 / 9.0
        const val DETECTED_FACE_ID = "DETECTED_FACE_ID"

        fun getIntent(context: Context): Intent = Intent(context, DetectorActivity::class.java)
    }
}
