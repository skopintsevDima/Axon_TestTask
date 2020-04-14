package com.test.axontest.app.di

import com.test.axontest.di.activity.ActivityComponent
import com.test.axontest.di.fragment.FragmentComponent
import dagger.Module

@Module(subcomponents = [
    ActivityComponent::class,
    FragmentComponent::class
])
class AppSubcomponentModule