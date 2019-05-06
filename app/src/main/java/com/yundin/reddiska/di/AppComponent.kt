package com.yundin.reddiska.di

import android.app.Application
import com.yundin.reddiska.ReddiskaApp
import com.yundin.reddiska.di.module.ActivityBuilderModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        ActivityBuilderModule::class
    ])
interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(app: Application): Builder

        fun build(): AppComponent
    }

    fun inject(app: ReddiskaApp)
}