package com.bresiu.codechallenge.viewmodels.uimodels;

public class ResultBundle<E> {
	private final E bundle;

	public ResultBundle(E bundle) {
		this.bundle = bundle;
	}

	public E getBundle() {
		return bundle;
	}
}