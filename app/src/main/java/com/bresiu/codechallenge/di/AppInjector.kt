package com.bresiu.codechallenge.di

import android.app.Activity
import android.app.Application
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.bresiu.codechallenge.CodeChallengeApp
import com.bresiu.codechallenge.di.component.DaggerAppComponent
import com.bresiu.codechallenge.presentation.activity.BaseActivity
import dagger.android.AndroidInjection
import dagger.android.support.AndroidSupportInjection

object AppInjector {

  fun init(codeChallengeApp: CodeChallengeApp) {
    DaggerAppComponent.builder().application(codeChallengeApp).build().inject(codeChallengeApp)
    codeChallengeApp.registerActivityLifecycleCallbacks(MyActivityLifecycleCallbacks())
  }

  private fun handleActivity(activity: Activity) {
    if (activity is Injectable) {
      AndroidInjection.inject(activity)
      (activity as BaseActivity).supportFragmentManager
          .registerFragmentLifecycleCallbacks(object : FragmentManager.FragmentLifecycleCallbacks() {
            override fun onFragmentCreated(fragmentManager: FragmentManager,
                                           fragment: Fragment, savedInstanceState: Bundle?) {
              if (fragment is Injectable) {
                AndroidSupportInjection.inject(fragment)
              }
            }
          }, true)
    }
  }

  private class MyActivityLifecycleCallbacks : Application.ActivityLifecycleCallbacks {
    override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
      if (activity is BaseActivity) {
        handleActivity(activity)
      }
    }

    override fun onActivityStarted(activity: Activity) {

    }

    override fun onActivityResumed(activity: Activity) {

    }

    override fun onActivityPaused(activity: Activity) {

    }

    override fun onActivityStopped(activity: Activity) {

    }

    override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {

    }

    override fun onActivityDestroyed(activity: Activity) {

    }
  }
}