package com.bresiu.codechallenge.data.entity;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "addresses", foreignKeys = @ForeignKey(entity = User.class, parentColumns = "id", childColumns = "userId", onDelete = ForeignKey.CASCADE))
public class Address {
	@PrimaryKey public int userId;
	public String street;
	public String suite;
	public String city;
	public String zipcode;

	public Address(int userId, String street, String suite, String city, String zipcode) {
		this.userId = userId;
		this.street = street;
		this.suite = suite;
		this.city = city;
		this.zipcode = zipcode;
	}
}
