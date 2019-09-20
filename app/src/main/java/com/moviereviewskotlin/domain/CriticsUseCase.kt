package com.moviereviewskotlin.domain

import com.moviereviewskotlin.data.critics.CriticsRepository
import com.moviereviewskotlin.data.critics.response.Critic
import com.moviereviewskotlin.data.critics.response.Critics
import io.reactivex.Observable
import javax.inject.Inject

class CriticsUseCase @Inject constructor(repository: CriticsRepository) : UseCase<String, Observable<MutableList<Critic>>>() {

    private val dataSource = repository

    override fun executeUseCase(requestValues: String): Observable<MutableList<Critic>> = dataSource.getCritics(requestValues)
}