package com.bresiu.codechallenge.model;

public class PostWithUserAddress {
	public String postTitle;
	public String userEmail;

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
