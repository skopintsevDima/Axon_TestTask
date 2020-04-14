package com.test.axontest.di.activity

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.test.axontest.detector.presentation.DetectorViewModel
import com.test.axontest.di.ViewModelFactory
import com.test.axontest.di.ViewModelKey
import com.test.axontest.di.fragment.FragmentScope
import com.test.axontest.face.presentation.DetectedFaceViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ActivityViewModelModule {
    @Binds
    @ActivityScope
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @ActivityScope
    @IntoMap
    @ViewModelKey(DetectorViewModel::class)
    internal abstract fun detectorViewModel(viewModel: DetectorViewModel): ViewModel
}