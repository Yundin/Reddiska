package com.yundin.reddiska.di.module

import com.yundin.reddiska.presentation.main.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface ActivityBuilderModule {

    @ContributesAndroidInjector
    fun contributeMainActivity(): MainActivity
}