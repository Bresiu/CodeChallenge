package com.bresiu.codechallenge.data.entity;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "companys", foreignKeys = @ForeignKey(entity = User.class, parentColumns = "id", childColumns = "userId", onDelete = ForeignKey.CASCADE))
public class Company {
	@PrimaryKey public int userId;
	public String name;
	public String catchPhrase;
	public String bs;

	public Company(int userId, String name, String catchPhrase, String bs) {
		this.userId = userId;
		this.name = name;
		this.catchPhrase = catchPhrase;
		this.bs = bs;
	}
}
