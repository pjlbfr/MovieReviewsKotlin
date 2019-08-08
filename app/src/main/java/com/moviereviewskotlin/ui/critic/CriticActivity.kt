package com.moviereviewskotlin.ui.critic

import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBar
import androidx.core.content.ContextCompat
import com.moviereviewskotlin.R
import com.moviereviewskotlin.base.BaseActivity
import com.moviereviewskotlin.data.critics.response.Critic
import kotlinx.android.synthetic.main.actionbar_title.*
import javax.inject.Inject

class CriticActivity : BaseActivity() {

    companion object {
        val TAG = CriticActivity::class.java.simpleName
    }

    @Inject
    lateinit var router: CriticRouter

    override fun getLayoutResource(): Int = R.layout.activity_critic

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val critic = intent.getParcelableExtra(TAG) as Critic

        initActionBar(critic.display_name)

        router.goToCriticFragment(supportFragmentManager, critic)
    }

    private fun initActionBar(name: String){
        supportActionBar?.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM
        supportActionBar?.setCustomView(R.layout.actionbar_title)
        supportActionBar?.setBackgroundDrawable(ColorDrawable(ContextCompat.getColor(this, R.color.colorCritics)))
        supportActionBar?.elevation = 0F
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        tvTitle.text = name
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            android.R.id.home -> super.onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }
}