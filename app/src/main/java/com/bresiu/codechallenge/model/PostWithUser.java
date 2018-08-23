package com.bresiu.codechallenge.model;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.room.Ignore;

public class PostWithUser implements Parcelable {
	public static final Creator<PostWithUser> CREATOR = new Creator<PostWithUser>() {
		@Override public PostWithUser createFromParcel(Parcel in) {
			return new PostWithUser(in);
		}

		@Override public PostWithUser[] newArray(int size) {
			return new PostWithUser[size];
		}
	};
	private long postId;
	private String postTitle;
	private String postBody;
	private String userEmail;
	private long userId;

	public PostWithUser() {
	}

	@Ignore protected PostWithUser(Parcel in) {
		postId = in.readLong();
		postTitle = in.readString();
		postBody = in.readString();
		userEmail = in.readString();
		userId = in.readLong();
	}

	@Override public void writeToParcel(Parcel dest, int flags) {
		dest.writeLong(postId);
		dest.writeString(postTitle);
		dest.writeString(postBody);
		dest.writeString(userEmail);
		dest.writeLong(userId);
	}

	@Override public int describeContents() {
		return 0;
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

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}
}
