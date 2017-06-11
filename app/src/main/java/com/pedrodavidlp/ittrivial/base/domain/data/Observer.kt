package com.pedrodavidlp.ittrivial.base.domain.data

interface Observer<in T> {
  fun onValueChange(newValue: T, oldValue: T)
}