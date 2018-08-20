package com.bresiu.codechallenge.repository.mappers

import java.util.*

object Mapper {
    fun <S, T> mapList(objectList: List<T>, objectMapper: ObjectMapper<S, T>): List<S> {
        val resultList = ArrayList<S>(objectList.size)
        for (`object` in objectList) {
            resultList.add(objectMapper.map(`object`))
        }
        return resultList
    }

    fun <S, T> mapObject(`object`: T, objectMapper: ObjectMapper<S, T>): S {
        return objectMapper.map(`object`)
    }

    interface ObjectMapper<S, T> {
        fun map(`object`: T): S
    }
}
