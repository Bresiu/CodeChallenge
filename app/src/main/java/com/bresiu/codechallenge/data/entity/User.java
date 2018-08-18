package com.bresiu.codechallenge.data.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "users") public class User {
	@PrimaryKey public long id;
	public String name;
	public String username;
	public String email;
	public String phone;
	public String website;

	public User(long id, String name, String username, String email, String phone, String website) {
		this.id = id;
		this.name = name;
		this.username = username;
		this.email = email;
		this.phone = phone;
		this.website = website;
	}
}
