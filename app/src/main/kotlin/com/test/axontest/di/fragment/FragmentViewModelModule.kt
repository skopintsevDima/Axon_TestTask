package com.test.axontest.di.fragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.test.axontest.di.ViewModelFactory
import com.test.axontest.di.ViewModelKey
import com.test.axontest.face.presentation.DetectedFaceViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class FragmentViewModelModule {
    @Binds
    @FragmentScope
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @FragmentScope
    @IntoMap
    @ViewModelKey(DetectedFaceViewModel::class)
    internal abstract fun detectedFaceViewModel(viewModel: DetectedFaceViewModel): ViewModel
}