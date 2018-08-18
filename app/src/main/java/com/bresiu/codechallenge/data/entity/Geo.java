package com.bresiu.codechallenge.data.entity;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

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
