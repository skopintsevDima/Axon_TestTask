package com.test.axontest.di.activity

import com.test.axontest.detector.domain.di.DetectorModule
import com.test.axontest.detector.presentation.DetectorActivity
import dagger.Subcomponent

@ActivityScope
@Subcomponent(modules = [
    ActivityViewModelModule::class,
    DetectorModule::class
])
interface ActivityComponent {
    @Subcomponent.Factory
    interface Factory {
        fun create(): ActivityComponent
    }

    fun inject(activity: DetectorActivity)
}