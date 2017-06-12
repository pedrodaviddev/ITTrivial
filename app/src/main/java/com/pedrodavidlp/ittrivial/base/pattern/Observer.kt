package com.pedrodavidlp.ittrivial.base.pattern

interface Observer<in T> {
  fun onValueChange(newValue: T, oldValue: T)
}