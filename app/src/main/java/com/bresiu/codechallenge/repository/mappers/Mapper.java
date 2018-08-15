package com.bresiu.codechallenge.repository.mappers;

import java.util.ArrayList;
import java.util.List;

public abstract class Mapper {
	public static <S, T> List<S> mapList(List<T> objectList, ObjectMapper<S, T> objectMapper) {
		List<S> resultList = new ArrayList<>(objectList.size());
		for (T object : objectList) {
			resultList.add(objectMapper.map(object));
		}
		return resultList;
	}

	public static <S, T> S mapObject(T object, ObjectMapper<S, T> objectMapper) {
		return objectMapper.map(object);
	}

	public interface ObjectMapper<S, T> {
		S map(T object);
	}
}
