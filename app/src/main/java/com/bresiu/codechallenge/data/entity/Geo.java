package com.bresiu.codechallenge.data.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "geos", foreignKeys = @ForeignKey(entity = Address.class, parentColumns = "userId", childColumns = "addressId", onDelete = ForeignKey.CASCADE))
public class Geo {
	@PrimaryKey public int addressId;
	public double lat;
	public double lng;

	public Geo(int addressId, double lat, double lng) {
		this.addressId = addressId;
		this.lat = lat;
		this.lng = lng;
	}
}
