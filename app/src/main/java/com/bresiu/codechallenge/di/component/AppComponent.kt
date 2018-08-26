package com.bresiu.codechallenge.di.component

import android.app.Application
import com.bresiu.codechallenge.CodeChallengeApp
import com.bresiu.codechallenge.di.module.AppModule
import com.bresiu.codechallenge.di.module.ItemDetailModule
import com.bresiu.codechallenge.di.module.ItemListModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [(AndroidInjectionModule::class),
      (AppModule::class),
      (ItemListModule::class),
      (ItemDetailModule::class)]
)
interface AppComponent {
  fun inject(codeChallengeApp: CodeChallengeApp)

  @Component.Builder
  interface Builder {
    @BindsInstance
    fun application(application: Application): Builder

    fun build(): AppComponent
  }
}
