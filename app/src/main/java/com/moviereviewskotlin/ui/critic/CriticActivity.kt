package com.moviereviewskotlin.ui.critic

import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.appcompat.app.ActionBar
import androidx.core.content.ContextCompat
import com.moviereviewskotlin.R
import com.moviereviewskotlin.base.BaseActivity
import com.moviereviewskotlin.data.critics.response.Critic
import kotlinx.android.synthetic.main.actionbar_title.*

class CriticActivity : BaseActivity() {

    companion object {
        val TAG = CriticActivity::class.java.simpleName
    }

    override fun getLayoutResource(): Int = R.layout.activity_critic

    lateinit var critic: Critic

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        critic = intent.getParcelableExtra(TAG)

        initActionBar()

    }

    private fun initActionBar(){
        supportActionBar?.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM
        supportActionBar?.setCustomView(R.layout.actionbar_title)
        supportActionBar?.setBackgroundDrawable(ColorDrawable(ContextCompat.getColor(this, R.color.colorCritics)))
        supportActionBar?.elevation = 0F
        tvTitle.text = critic.display_name
    }

}