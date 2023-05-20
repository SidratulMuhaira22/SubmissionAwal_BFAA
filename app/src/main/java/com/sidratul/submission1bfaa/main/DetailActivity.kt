package com.sidratul.submission1bfaa.main

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.annotation.StringRes
import com.bumptech.glide.Glide
import com.google.android.material.tabs.TabLayoutMediator
import com.sidratul.submission1bfaa.R
import com.sidratul.submission1bfaa.adapter.SectionPagerAdapter
import com.sidratul.submission1bfaa.data.GithubResponse
import com.sidratul.submission1bfaa.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

    private val detailViewModel: DetailViewModel by viewModels()
    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val user = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra<GithubResponse>(EXTRA_USER)
        }else{
            @Suppress("DEPRECATION")
            intent.getParcelableExtra<GithubResponse>(EXTRA_USER)
        }

        supportActionBar?.title = "Detail User"

        if (user != null && detailViewModel.DetailUser.value == null){
            detailViewModel.getUser(user.login)
        }

        detailViewModel.DetailUser.observe(this) {
            binding.apply {
                Glide.with(this@DetailActivity)
                    .load(it.avatarUrl)
                    .into(gambar)
                name.text = it.name
                username.text = it.login
                Follower.text = it.followers.toString()
                Following.text = it.following.toString()
            }
        }

        detailViewModel.isLoading.observe(this){
            showLoading(it)
        }

        val sectionPagerAdapter = SectionPagerAdapter(this)
        binding.viewPager.adapter = sectionPagerAdapter

        TabLayoutMediator(binding.tabs, binding.viewPager){ tab, position ->
            tab.text = resources.getString(TAB_TITLES[position])
        }.attach()

        supportActionBar?.elevation = 0f

    }

    private fun showLoading(isLoading: Boolean) {
        if (isLoading){
            binding.progrerssBar.visibility = View.VISIBLE
        }else{
            binding.progrerssBar.visibility = View.GONE
        }
    }

    companion object{
        private const val TAG = "DetailActivity"
        const val EXTRA_USER = "extra_user"
        @StringRes
        private val TAB_TITLES = intArrayOf(
            R.string.follower,
            R.string.following
        )
    }
}