package com.bresiu.codechallenge.model;

import com.bresiu.codechallenge.data.entity.Address;
import com.bresiu.codechallenge.data.entity.Company;
import com.bresiu.codechallenge.data.entity.Geo;
import com.bresiu.codechallenge.data.entity.User;
import java.util.List;

public class UserCombined {
	public List<User> users;
	public List<Address> addresses;
	public List<Geo> geos;
	public List<Company> companies;

	public UserCombined(List<User> users, List<Address> addresses, List<Geo> geos,
			List<Company> companies) {
		this.users = users;
		this.addresses = addresses;
		this.geos = geos;
		this.companies = companies;
	}
}
