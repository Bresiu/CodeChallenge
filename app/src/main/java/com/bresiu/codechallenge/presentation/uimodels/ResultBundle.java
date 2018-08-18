package com.bresiu.codechallenge.presentation.uimodels;

public class ResultBundle<E> {
	private final E bundle;

	public ResultBundle(E bundle) {
		this.bundle = bundle;
	}

	public E unpack() {
		return bundle;
	}
}