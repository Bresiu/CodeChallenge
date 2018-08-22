package com.bresiu.codechallenge.di;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import com.bresiu.codechallenge.CodeChallengeApp;
import com.bresiu.codechallenge.di.component.DaggerAppComponent;
import com.bresiu.codechallenge.presentation.activity.BaseActivity;
import dagger.android.AndroidInjection;
import dagger.android.support.AndroidSupportInjection;

public class AppInjector {
	private AppInjector() {
	}

	public static void init(CodeChallengeApp codeChallengeApp) {
		DaggerAppComponent.builder().application(codeChallengeApp).build().inject(codeChallengeApp);
		codeChallengeApp.registerActivityLifecycleCallbacks(
				new Application.ActivityLifecycleCallbacks() {
					@Override public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
						if (activity instanceof BaseActivity) {
							handleActivity(activity);
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

	private static void handleActivity(Activity activity) {
		if (activity instanceof Injectable) {
			AndroidInjection.inject(activity);
			((BaseActivity) activity).getSupportFragmentManager()
					.registerFragmentLifecycleCallbacks(new FragmentManager.FragmentLifecycleCallbacks() {
						@Override public void onFragmentCreated(@NonNull FragmentManager fragmentManager,
								@NonNull Fragment fragment, Bundle savedInstanceState) {
							if (fragment instanceof Injectable) {
								AndroidSupportInjection.inject(fragment);
							}
						}
					}, true);
		}
	}
}