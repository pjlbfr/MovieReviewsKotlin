package com.moviereviewskotlin.ui.critic

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.text.HtmlCompat
import com.bumptech.glide.request.RequestOptions
import com.moviereviewskotlin.GlideApp
import com.moviereviewskotlin.R
import com.moviereviewskotlin.base.BaseFragment
import com.moviereviewskotlin.data.critics.response.Critic
import kotlinx.android.synthetic.main.fragment_critic.*

class CriticFragment : BaseFragment() {


    companion object {
        val TAG = CriticFragment::class.java.simpleName

        fun newInstance(critic: Critic): CriticFragment {

            val bundle = Bundle()
            bundle.putParcelable(TAG, critic)
            val fragment = CriticFragment()
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun getFragmentLayout(): Int = R.layout.fragment_critic

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val critic = arguments?.getParcelable(TAG) as Critic?

        tvNameCritic.text = critic?.display_name

        tvStatusCritic.text = critic?.status

        val src = critic?.multimedia?.resource?.src

        GlideApp.with(this)
            .load(src)
            .apply(RequestOptions().placeholder(R.drawable.critic_default).centerCrop())
            .into(imageCritic)

        if (!critic?.bio.equals("")) {

            tvBioCritic.visibility = View.VISIBLE
            tvBioCritic.text = HtmlCompat.fromHtml(critic!!.bio, HtmlCompat.FROM_HTML_MODE_LEGACY)

            cardCritic.setOnTouchListener(object : OnSwipeTouchListener(context) {
                override fun onSwipeTop() {
                    tvBioCritic.visibility = View.GONE
                }

                override fun onSwipeBottom() {
                    tvBioCritic.visibility = View.VISIBLE
                }
            })
        }
    }



}