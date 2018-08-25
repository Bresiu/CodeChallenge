package com.bresiu.codechallenge.repository.mappers

import java.util.*

object Mapper {
  fun <S, T> mapList(objectList: List<T>, objectMapper: ObjectMapper<S, T>): List<S> {
    val resultList = ArrayList<S>(objectList.size)
    for (mappingItem in objectList) {
      resultList.add(objectMapper.map(mappingItem))
    }
    return resultList
  }

  fun <S, T> unfoldList(objectList: List<T>, objectMapper: ObjectMapper<List<S>, T>): List<S> {
    val resultList = ArrayList<S>()
    for (listItem in objectList) {
      resultList.addAll(objectMapper.map(listItem))
    }
    return resultList
  }

  fun <S, T> mapObject(mappingItem: T, objectMapper: ObjectMapper<S, T>): S {
    return objectMapper.map(mappingItem)
  }

  interface ObjectMapper<S, T> {
    fun map(mappingItem: T): S
  }
}
