package com.test.axontest.face.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.squareup.picasso.Picasso
import com.squareup.picasso.Transformation
import com.test.axontest.R
import com.test.axontest.app.App
import com.test.axontest.sessions.domain.model.DetectedFace
import com.test.axontest.util.showBottomMsg
import kotlinx.android.synthetic.main.fragment_detected_face.*
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class DetectedFaceFragment: Fragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: DetectedFaceViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_detected_face, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initDependencies()
        initUI()
    }

    private fun initDependencies() {
        App.appComponent.fragmentComponent().create().inject(this)
        viewModel = viewModelFactory.create(DetectedFaceViewModel::class.java)
    }

    private fun initUI() {
        btnOk.setOnClickListener { findNavController().navigateUp() }
        arguments?.let { args ->
            val detectedFaceId = DetectedFaceFragmentArgs.fromBundle(args).detectedFaceId
            if (detectedFaceId != -1L) {
                viewModel.getDetectedFace(detectedFaceId).observe(
                    viewLifecycleOwner,
                    Observer { handleDetectedFaceRes(it) }
                )
            }
        }
    }

    private fun handleDetectedFaceRes(detectedFaceRes: Result<DetectedFace>) {
        detectedFaceRes.fold(
            {
                val picasso = Picasso.get()
                picasso.load(it.photoUri)
                    .resize(originalPhotoView.width, originalPhotoView.height)
                    .centerCrop()
                    .into(originalPhotoView)

                val density = resources.displayMetrics.density
                picasso.load(it.photoUri)
                    .transform(CropTransformation(it.top, it.left, it.width, it.height, density))
                    .into(facePhotoView)

                dateView.text = SimpleDateFormat(
                    "yyyy-MM-dd HH:mm:ss",
                    Locale.getDefault()
                ).format(it.timestamp)
            },
            { showBottomMsg(rootLayout, R.string.loading_photo_error) }
        )
    }
}