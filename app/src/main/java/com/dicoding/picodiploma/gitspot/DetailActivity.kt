package com.dicoding.picodiploma.gitspot

import android.content.Intent.EXTRA_USER
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.dicoding.picodiploma.gitspot.data.User
import com.dicoding.picodiploma.gitspot.data.UserData
import com.dicoding.picodiploma.gitspot.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val layoutManager = LinearLayoutManager(this)

        val user = intent.getParcelableExtra<User>(EXTRA_USER)
        supportActionBar?.title = user?.name
        tvLocation.text = user?.location
        tvBio.text = user?.bio

    }

    private fun displayUserData(user: UserData) {
        binding.apply {
            tvUsername.text = user.login
            tvName.text = user.name
            tvFollowing.text = user.following.toString()
            tvFollower.text = user.follower.toString()
            Glide.with(this@DetailActivity)
                .load(user.avatarUrl)
                .into(ivAvatar)
        }
    }

}