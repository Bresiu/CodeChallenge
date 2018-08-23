package com.bresiu.codechallenge.presentation.uimodels

class Result<B> private constructor(val state: String, val error: Throwable?,
                                    val bundle: ResultBundle<B>?) {
    companion object {
        const val LOADING = "loading"
        const val ERROR = "error"
        const val SUCCESS = "success"

        fun <B> loadingResult(): Result<B> {
            return Result(LOADING, null, null)
        }

        fun <B> errorResult(error: Throwable): Result<B> {
            return Result(ERROR, error, null)
        }

        fun <B> successResult(bundle: ResultBundle<B>): Result<B> {
            return Result(SUCCESS, null, bundle)
        }
    }
}
