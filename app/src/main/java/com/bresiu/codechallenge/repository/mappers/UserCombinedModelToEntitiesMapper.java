package com.bresiu.codechallenge.repository.mappers;

import com.bresiu.codechallenge.data.entity.Address;
import com.bresiu.codechallenge.data.entity.Company;
import com.bresiu.codechallenge.data.entity.Geo;
import com.bresiu.codechallenge.model.User;
import com.bresiu.codechallenge.model.UserCombined;
import java.util.ArrayList;
import java.util.List;

public class UserCombinedModelToEntitiesMapper implements Mapper.ObjectMapper<UserCombined, List<User>> {
	@Override public UserCombined map(List<User> userModels) {
		List<com.bresiu.codechallenge.data.entity.User> userEntities =
				new ArrayList<>(userModels.size());
		List<com.bresiu.codechallenge.data.entity.Address> addressEntities =
				new ArrayList<>(userModels.size());
		List<com.bresiu.codechallenge.data.entity.Geo> geoEntities = new ArrayList<>(userModels.size());
		List<com.bresiu.codechallenge.data.entity.Company> companyEntities =
				new ArrayList<>(userModels.size());
		for (User user : userModels) {
			userEntities.add(
					new com.bresiu.codechallenge.data.entity.User(user.id, user.name, user.username,
							user.email, user.phone, user.website));
			addressEntities.add(new Address(user.id, user.address.street, user.address.suite, user.address.city, user.address.zipcode));
			geoEntities.add(new Geo(user.id, user.address.geo.lat, user.address.geo.lng));
			companyEntities.add(
					new Company(user.id, user.company.name, user.company.catchPhrase, user.company.bs));
		}
		return new UserCombined(userEntities, addressEntities, geoEntities, companyEntities);
	}
}
