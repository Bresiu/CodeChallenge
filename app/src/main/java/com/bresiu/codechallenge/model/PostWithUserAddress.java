package com.bresiu.codechallenge.model;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;

public class PostWithUserAddress implements Parcelable {
	public static final Creator<PostWithUserAddress> CREATOR = new Creator<PostWithUserAddress>() {
		@Override public PostWithUserAddress createFromParcel(Parcel in) {
			return new PostWithUserAddress(in);
		}

		@Override public PostWithUserAddress[] newArray(int size) {
			return new PostWithUserAddress[size];
		}
	};
	private long postId;
	private String postTitle;
	private String postBody;
	private String userEmail;

	public PostWithUserAddress() {
	}

	protected PostWithUserAddress(Parcel in) {
		postId = in.readLong();
		postTitle = in.readString();
		postBody = in.readString();
		userEmail = in.readString();
	}

	public long getPostId() {
		return postId;
	}

	public void setPostId(long postId) {
		this.postId = postId;
	}

	public String getPostTitle() {
		return postTitle;
	}

	public void setPostTitle(String postTitle) {
		this.postTitle = postTitle;
	}

	public String getPostBody() {
		return postBody;
	}

	public void setPostBody(String postBody) {
		this.postBody = postBody;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	@NonNull @Override public String toString() {
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

	@Override public int describeContents() {
		return 0;
	}

	@Override public void writeToParcel(Parcel dest, int flags) {
		dest.writeLong(postId);
		dest.writeString(postTitle);
		dest.writeString(postBody);
		dest.writeString(userEmail);
	}
}
