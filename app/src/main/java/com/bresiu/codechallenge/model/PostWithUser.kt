package com.bresiu.codechallenge.model

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Ignore

open class PostWithUser : Parcelable {
  var postId: Long = 0
  var postTitle: String? = null
  var postBody: String? = null
  var userEmail: String? = null
  var userId: Long = 0

  constructor()

  @Ignore protected constructor(`in`: Parcel) {
    postId = `in`.readLong()
    postTitle = `in`.readString()
    postBody = `in`.readString()
    userEmail = `in`.readString()
    userId = `in`.readLong()
  }

  override fun writeToParcel(dest: Parcel, flags: Int) {
    dest.writeLong(postId)
    dest.writeString(postTitle)
    dest.writeString(postBody)
    dest.writeString(userEmail)
    dest.writeLong(userId)
  }

  override fun describeContents(): Int {
    return 0
  }

  companion object CREATOR : Parcelable.Creator<PostWithUser> {
    override fun createFromParcel(parcel: Parcel): PostWithUser {
      return PostWithUser(parcel)
    }

    override fun newArray(size: Int): Array<PostWithUser?> {
      return arrayOfNulls(size)
    }
  }
}
