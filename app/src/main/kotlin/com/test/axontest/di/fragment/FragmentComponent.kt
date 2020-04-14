package com.test.axontest.di.fragment

import com.test.axontest.face.domain.di.DetectedFaceModule
import com.test.axontest.face.presentation.DetectedFaceFragment
import dagger.Subcomponent

@FragmentScope
@Subcomponent(modules = [
    FragmentViewModelModule::class,
    DetectedFaceModule::class
])
interface FragmentComponent {
    @Subcomponent.Factory
    interface Factory {
        fun create(): FragmentComponent
    }

    fun inject(fragment: DetectedFaceFragment)
}