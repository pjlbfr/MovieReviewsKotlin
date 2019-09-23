package com.moviereviewskotlin.data.critics.local

import com.moviereviewskotlin.data.critics.response.Critic
import com.moviereviewskotlin.data.critics.response.Critics
import com.moviereviewskotlin.room.dao.CriticDao
import io.reactivex.Observable
import javax.inject.Inject

class CriticLocalStore @Inject constructor(private val criticDao: CriticDao) {

    fun getCritics(): Observable<MutableList<Critic>> =
        Observable.create { e ->
            val result = criticDao.getAll()
            e.onNext(result)
            e.onComplete()
        }

    fun updateCritics(critics: Critics): Observable<MutableList<Critic>> =
        Observable.create { e ->
            criticDao.deleteAll()
            criticDao.insert(critics.results!!)
            e.onNext(critics.results)
            e.onComplete()
        }
}
