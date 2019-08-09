package com.moviereviewskotlin.domain

abstract class UseCase<Q, P> {

    fun run(requestValues: Q): P = executeUseCase(requestValues)

    protected abstract fun executeUseCase(requestValues: Q): P
}