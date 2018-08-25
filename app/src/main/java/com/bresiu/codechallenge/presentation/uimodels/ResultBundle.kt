package com.bresiu.codechallenge.presentation.uimodels

class ResultBundle<E>(private val bundle: E) {

  fun unpack(): E {
    return bundle
  }
}