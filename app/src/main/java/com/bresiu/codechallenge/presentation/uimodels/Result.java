package com.bresiu.codechallenge.presentation.uimodels;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public final class Result<B> {
	public static final String LOADING = "loading";
	public static final String ERROR = "error";
	public static final String SUCCESS = "success";
	@NonNull private final String state;
	@Nullable private final Throwable error;
	@Nullable private ResultBundle<B> resultBundle;

	private Result(@NonNull String state, @Nullable Throwable error,
			@Nullable ResultBundle<B> resultBundle) {
		this.state = state;
		this.error = error;
		this.resultBundle = resultBundle;
	}

	public static <B> Result<B> loadingResult() {
		return new Result<>(LOADING, null, null);
	}

	public static <B> Result<B> errorResult(Throwable error) {
		return new Result<>(ERROR, error, null);
	}

	public static <B> Result<B> successResult(ResultBundle<B> bundle) {
		return new Result<>(SUCCESS, null, bundle);
	}

	@NonNull public String getState() {
		return state;
	}

	@Nullable public Throwable getError() {
		return error;
	}

	public ResultBundle<B> getBundle() {
		return resultBundle;
	}
}
