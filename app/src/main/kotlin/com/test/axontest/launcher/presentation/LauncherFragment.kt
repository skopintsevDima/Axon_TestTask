package com.test.axontest.launcher.presentation

import android.Manifest
import android.app.Activity.RESULT_OK
import android.content.Intent
import android.content.pm.PackageManager.PERMISSION_GRANTED
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat.shouldShowRequestPermissionRationale
import androidx.core.content.ContextCompat.checkSelfPermission
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.test.axontest.R
import com.test.axontest.detector.presentation.DetectorActivity
import com.test.axontest.detector.presentation.DetectorActivity.Companion.DETECTED_FACE_ID
import com.test.axontest.launcher.presentation.LauncherFragmentDirections.Companion.actionLauncherFragmentToDetectedFaceFragment
import com.test.axontest.util.showBottomMsg
import kotlinx.android.synthetic.main.fragment_launcher.*

class LauncherFragment: Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_launcher, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
    }

    private fun initUI() {
        startBtn.setOnClickListener { if (hasPermissions()) launchFaceDetector() }
        listBtn.setOnClickListener {
            val destination = LauncherFragmentDirections.actionLauncherFragmentToSessionsFragment()
            findNavController().navigate(destination)
        }
    }

    private fun hasPermissions(): Boolean {
        var hasPermissions = true
        activity?.run {
            if (checkSelfPermission(this, PERMISSION_CAMERA) != PERMISSION_GRANTED) {
                hasPermissions = false
                if (shouldShowRequestPermissionRationale(this, PERMISSION_CAMERA)) {
                    showPermissionsRationale()
                } else {
                    requestCameraPermission()
                }
            }
        }
        return hasPermissions
    }

    private fun showPermissionsRationale() {
        showBottomMsg(rootLayout, R.string.permissions_rationale, R.string.permissions_rationale_action) {
            requestCameraPermission()
        }
    }

    private fun requestCameraPermission() {
        requestPermissions(arrayOf(PERMISSION_CAMERA), RC_CAMERA_PERMISSION)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when (requestCode) {
            RC_CAMERA_PERMISSION -> {
                if ((grantResults.isNotEmpty() && grantResults[0] == PERMISSION_GRANTED)) {
                    launchFaceDetector()
                }
                return
            }
        }
    }

    private fun launchFaceDetector(){
        activity?.run { this@LauncherFragment.startActivityForResult(
            DetectorActivity.getIntent(this),
            RC_DETECT_FACE
        ) }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        when (requestCode){
            RC_DETECT_FACE -> {
                if (resultCode == RESULT_OK) {
                    data?.let {
                        val detectedFaceId = it.getLongExtra(DETECTED_FACE_ID, -1L)
                        val destination = actionLauncherFragmentToDetectedFaceFragment(detectedFaceId)
                        findNavController().navigate(destination)
                    }
                } else {
                    showBottomMsg(rootLayout, R.string.face_detection_error)
                }
            }
        }
    }



    companion object {
        const val PERMISSION_CAMERA = Manifest.permission.CAMERA
        const val RC_CAMERA_PERMISSION = 100
        const val RC_DETECT_FACE = 200
    }
}