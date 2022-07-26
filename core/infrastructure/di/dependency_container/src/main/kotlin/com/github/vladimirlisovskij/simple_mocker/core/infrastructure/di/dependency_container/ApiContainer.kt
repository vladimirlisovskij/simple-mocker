package com.github.vladimirlisovskij.simple_mocker.core.infrastructure.di.dependency_container

abstract class ApiContainer<T> {
    val instance by lazy { createApi() }
    abstract fun createApi(): T
}
