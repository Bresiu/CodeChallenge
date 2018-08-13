package com.bresiu.codechallenge.dependencyinjection

import android.app.Application
import com.bresiu.codechallenge.CodeChallengeApp
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [(AndroidInjectionModule::class), (AppModule::class), (ItemListActivityModule::class)])
interface AppComponent {
    fun inject(codeChallengeApp: CodeChallengeApp)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }
}
