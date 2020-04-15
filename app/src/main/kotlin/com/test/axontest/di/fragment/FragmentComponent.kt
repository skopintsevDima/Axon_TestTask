package com.test.axontest.di.fragment

import com.test.axontest.face.domain.di.DetectedFaceModule
import com.test.axontest.face.presentation.DetectedFaceFragment
import com.test.axontest.sessions.domain.di.SessionsModule
import com.test.axontest.sessions.presentation.SessionsFragment
import dagger.Subcomponent

@FragmentScope
@Subcomponent(modules = [
    FragmentViewModelModule::class,
    DetectedFaceModule::class,
    SessionsModule::class
])
interface FragmentComponent {
    @Subcomponent.Factory
    interface Factory {
        fun create(): FragmentComponent
    }

    fun inject(fragment: DetectedFaceFragment)
    fun inject(fragment: SessionsFragment)
}