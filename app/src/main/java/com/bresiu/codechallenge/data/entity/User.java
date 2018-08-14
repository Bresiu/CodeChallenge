package com.bresiu.codechallenge.data.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "users") public class User {
	@PrimaryKey long id;
	String name;
	String username;
	String email;
	@Embedded(prefix = "address") Address address;
	String phone;
	String website;
	@Embedded(prefix = "company") Company company;

	public User(long id, String name, String username, String email, Address address, String phone,
			String website, Company company) {
		this.id = id;
		this.name = name;
		this.username = username;
		this.email = email;
		this.address = address;
		this.phone = phone;
		this.website = website;
		this.company = company;
	}

	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getUsername() {
		return username;
	}

	public String getEmail() {
		return email;
	}

	public Address getAddress() {
		return address;
	}

	public String getPhone() {
		return phone;
	}

	public String getWebsite() {
		return website;
	}

	public Company getCompany() {
		return company;
	}

	public class Address {
		String street;
		String suite;
		String city;
		String zipcode;
		@Embedded(prefix = "geo") Geo geo;

		public Address(String street, String suite, String city, String zipcode, Geo geo) {
			this.street = street;
			this.suite = suite;
			this.city = city;
			this.zipcode = zipcode;
			this.geo = geo;
		}

		public String getStreet() {
			return street;
		}

		public String getSuite() {
			return suite;
		}

		public String getCity() {
			return city;
		}

		public String getZipcode() {
			return zipcode;
		}

		public Geo getGeo() {
			return geo;
		}
	}

	public class Geo {
		double lat;
		double lng;

		public Geo(double lat, double lng) {
			this.lat = lat;
			this.lng = lng;
		}

		public double getLat() {
			return lat;
		}

		public double getLng() {
			return lng;
		}
	}

	public class Company {
		String name;
		@ColumnInfo(name = "catch_phrase") String catchPhrase;
		String bs;

		public Company(String name, String catchPhrase, String bs) {
			this.name = name;
			this.catchPhrase = catchPhrase;
			this.bs = bs;
		}

		public String getName() {
			return name;
		}

		public String getCatchPhrase() {
			return catchPhrase;
		}

		public String getBs() {
			return bs;
		}
	}
}
