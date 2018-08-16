package com.bresiu.codechallenge.viewmodels.uimodels;

private class ResultBundle<E extends BaseEvent, B> {

	private final String event;
	private final B bundle;

	ResultBundle(E event, B bundle) {
		this.event = event.getClass().getSimpleName();
		this.bundle = bundle;
	}
	//... getters
}