package com.bresiu.codechallenge.model

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Ignore
import androidx.room.Relation
import com.bresiu.codechallenge.data.entity.Photo

class AlbumWithPhotos : Parcelable {
    var id: Long = 0
    var title: String? = null
    @Relation(parentColumn = "id", entityColumn = "albumId")
    var photos: List<Photo>? = null

    constructor()

    @Ignore private constructor(`in`: Parcel) {
        id = `in`.readLong()
        title = `in`.readString()
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeLong(id)
        dest.writeString(title)
    }

    companion object CREATOR : Parcelable.Creator<AlbumWithPhotos> {
        override fun createFromParcel(parcel: Parcel): AlbumWithPhotos {
            return AlbumWithPhotos(parcel)
        }

        override fun newArray(size: Int): Array<AlbumWithPhotos?> {
            return arrayOfNulls(size)
        }
    }
}
