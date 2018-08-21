package com.bresiu.codechallenge.di;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import com.bresiu.codechallenge.CodeChallengeApp;
import com.bresiu.codechallenge.di.component.DaggerAppComponent;
import com.bresiu.codechallenge.presentation.activity.BaseActivity;
import dagger.android.AndroidInjection;

public class AppInjector {
	private AppInjector() {
	}

	public static void init(CodeChallengeApp codeChallengeApp) {
		DaggerAppComponent.builder().application(codeChallengeApp).build().inject(codeChallengeApp);
		codeChallengeApp.registerActivityLifecycleCallbacks(new Application.ActivityLifecycleCallbacks() {
			@Override public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
				if (activity instanceof BaseActivity) {
					AndroidInjection.inject(activity);
				}
			}

			@Override public void onActivityStarted(Activity activity) {

			}

			@Override public void onActivityResumed(Activity activity) {

			}

			@Override public void onActivityPaused(Activity activity) {

			}

			@Override public void onActivityStopped(Activity activity) {

			}

			@Override public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

			}

			@Override public void onActivityDestroyed(Activity activity) {

			}
		});
	}
}