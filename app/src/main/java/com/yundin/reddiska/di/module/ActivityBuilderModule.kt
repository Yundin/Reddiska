package com.yundin.reddiska.di.module

import com.yundin.reddiska.presentation.main.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.android.support.AndroidSupportInjectionModule

@Module(
    includes = [
        AndroidSupportInjectionModule::class,
        ViewModelModule::class,
        RepositoryModule::class,
        PersistenceModule::class,
        NetworkModule::class
    ]
)
interface ActivityBuilderModule {

    @ContributesAndroidInjector
    fun contributeMainActivity(): MainActivity
}