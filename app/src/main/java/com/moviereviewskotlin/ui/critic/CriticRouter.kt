package com.moviereviewskotlin.ui.critic

import androidx.fragment.app.FragmentManager
import com.moviereviewskotlin.base.BaseRouter
import com.moviereviewskotlin.data.critics.response.Critic
import javax.inject.Inject

class CriticRouter @Inject constructor() : BaseRouter() {

    fun goToCriticFragment(fm: FragmentManager, critic: Critic){
        fm.beginTransaction()
            .add(containerId, CriticFragment.newInstance(critic))
            .commit()
    }
}