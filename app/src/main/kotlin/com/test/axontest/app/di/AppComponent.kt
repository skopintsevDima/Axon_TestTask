package com.test.axontest.app.di

import android.content.Context
import com.test.axontest.db.di.DbModule
import com.test.axontest.di.activity.ActivityComponent
import com.test.axontest.di.fragment.FragmentComponent
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AppModule::class,
    AppSubcomponentModule::class,
    DbModule::class
])
interface AppComponent {
    fun applicationContext(): Context

    fun activityComponent(): ActivityComponent.Factory
    fun fragmentComponent(): FragmentComponent.Factory
}