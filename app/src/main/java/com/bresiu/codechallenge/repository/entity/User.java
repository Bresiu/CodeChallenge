package com.bresiu.codechallenge.repository.entity;

public class User {
	long id;
	String name;
	String username;
	String email;
	Address address;
	String phone;
	String website;
	Company company;

	private class Address {
		String street;
		String suite;
		String city;
		String zipcode;
		Geo geo;

		private class Geo {
			double lat;
			double lng;
		}
	}

	private class Company {
		String name;
		String catchPhrase;
		String bs;
	}
}
