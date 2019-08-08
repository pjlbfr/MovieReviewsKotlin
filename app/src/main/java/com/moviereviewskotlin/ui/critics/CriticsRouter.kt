package com.moviereviewskotlin.ui.critics

import android.app.Activity
import android.content.Intent
import com.moviereviewskotlin.data.critics.response.Critic
import com.moviereviewskotlin.ui.critic.CriticActivity
import javax.inject.Inject

class CriticsRouter @Inject constructor() {

    fun goToCriticView(activity: Activity, critic: Critic){
        val intent = Intent(activity, CriticActivity::class.java)
        intent.putExtra(CriticActivity.TAG, critic)
        activity.startActivity(intent)
    }
}