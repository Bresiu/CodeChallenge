package com.bresiu.codechallenge.model;

public class PostWithUserAddress {
	public String postTitle;
	public String userEmail;

	public String getPostTitle() {
		return postTitle;
	}

	public String getUserEmail() {
		return userEmail;
	}

	@Override public String toString() {
		return "PostWithUserAddress{"
				+ "postTitle='"
				+ postTitle
				+ '\''
				+ ", userEmail='"
				+ userEmail
				+ '\''
				+ '}';
	}
}
