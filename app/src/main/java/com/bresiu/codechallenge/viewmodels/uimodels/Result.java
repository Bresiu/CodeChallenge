package com.bresiu.codechallenge.viewmodels.uimodels;

public final class Result<B> {
	private static final String LOADING = "loading";
	private static final String ERROR = "error";
	private static final String SUCCESS = "success";
	private final String state;
	private final boolean isLoading;
	private final boolean isSuccessful;
	private final Throwable error;
	private ResultBundle resultBundle;

	public Result(String state, boolean isLoading, boolean isSuccessful, Throwable error,
			ResultBundle resultBundle) {
		this.state = state;
		this.isLoading = isLoading;
		this.isSuccessful = isSuccessful;
		this.error = error;
		this.resultBundle = resultBundle;
	}

	public static <B> Result<B> loadingResult() {
		return new Result<>(LOADING, true, false, null, null);
	}

	public static <B> Result<B> errorResult(Throwable error) {
		return new Result<>(ERROR, false, false, error, null);
	}

	public static <B> Result<B> successResult(ResultBundle<?, B> bundle) {
		return new Result<>(SUCCESS, false, true, null, bundle);
	}
}
