package com.pedrodavidlp.ittrivial.base

interface Observer<T> {
  fun onChange(newValue: T)
}