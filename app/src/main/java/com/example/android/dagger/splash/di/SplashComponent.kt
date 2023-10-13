package com.example.android.dagger.splash.di

import com.example.android.dagger.di.ActivityScope
import com.example.android.dagger.splash.ui.SplashActivity
import dagger.Subcomponent

@ActivityScope
@Subcomponent
interface SplashComponent {


    @Subcomponent.Factory
    interface Factory {
        fun create(): SplashComponent
    }

    fun inject(activity: SplashActivity)
}