package com.bresiu.codechallenge.di

import android.app.Activity
import android.app.Application
import android.os.Bundle
import com.bresiu.codechallenge.CodeChallengeApp
import com.bresiu.codechallenge.di.component.DaggerAppComponent
import com.bresiu.codechallenge.presentation.activity.BaseActivity
import dagger.android.AndroidInjection

object AppInjector {

    fun init(codeChallengeApp: CodeChallengeApp) {
        DaggerAppComponent.builder().application(codeChallengeApp).build().inject(codeChallengeApp)
        codeChallengeApp.registerActivityLifecycleCallbacks(object : Application.ActivityLifecycleCallbacks {
            override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle) {
                if (activity is BaseActivity) {
                    AndroidInjection.inject(activity)
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
        })
    }
}