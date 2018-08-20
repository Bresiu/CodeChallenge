package com.bresiu.codechallenge.model;

public class PostWithUserAddress {
	private String postTitle;
	private long postId;
	private String userEmail;

	public String getPostTitle() {
		return postTitle;
	}

	public long getPostId() {
		return postId;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setPostTitle(String postTitle) {
		this.postTitle = postTitle;
	}

	public void setPostId(long postId) {
		this.postId = postId;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	@Override public String toString() {
		return "PostWithUserAddress{"
				+ "postTitle='"
				+ postTitle
				+ '\''
				+ ", postId="
				+ postId
				+ ", userEmail='"
				+ userEmail
				+ '\''
				+ '}';
	}
}
