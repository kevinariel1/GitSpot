package com.dicoding.picodiploma.gitspot

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.annotation.StringRes
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.dicoding.picodiploma.gitspot.adapter.SectionsPagerAdapter
import com.dicoding.picodiploma.gitspot.data.UserData
import com.dicoding.picodiploma.gitspot.databinding.ActivityDetailBinding
import com.dicoding.picodiploma.gitspot.viewmodel.UserViewModel
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

private const val TAG = "DetailActivity"
class DetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_USER = "extra_user"
        @StringRes
        private val TAB_TITLES = intArrayOf(
            R.string.tab_text_1,
            R.string.tab_text_2
        )
    }

    private lateinit var binding: ActivityDetailBinding
    private val userVm: UserViewModel by viewModels()
    private lateinit var username: String

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val user = intent?.getParcelableExtra<UserData>(EXTRA_USER)
        Log.d(TAG, "parcelable: $user")
        user?.let {
            userVm.getUserDetail(it.login!!)
            username = it.login!!
        }
        val sectionsPagerAdapter = SectionsPagerAdapter(this, username)
        val viewPager: ViewPager2 = findViewById(R.id.viewPager)
        viewPager.adapter = sectionsPagerAdapter
        val tabs : TabLayout = findViewById(R.id.tabLayout)
        TabLayoutMediator(tabs, viewPager) { tab, position ->
            tab.text = resources.getString(TAB_TITLES[position])
        }.attach()

        supportActionBar?.apply {
            title = resources.getString(R.string.detail_user)
            setDisplayHomeAsUpEnabled(true)
        }

        userVm.userDetail.observe(this) { userDetail ->
            // update the UI with the user detail
            Log.d("TAG", "onCreate: $userDetail")
            Glide.with(this)
                .load(userDetail.avatarUrl)
                .into(binding.ivAvatar)
            binding.tvName.text = userDetail.name
            binding.tvUsername.text = userDetail.login
            binding.tvFollowerCount.text =  userDetail.followers.toString() + " Followers"
            binding.tvFollowingCount.text = userDetail.following.toString() + " Following"
            // ...
        }
    }

    override fun onStart() {
        super.onStart()
        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                // Handle back button event
                finish()
            }
        }
        onBackPressedDispatcher.addCallback(this, callback)
    }
}