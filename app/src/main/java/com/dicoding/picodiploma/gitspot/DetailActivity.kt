package com.dicoding.picodiploma.gitspot

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import com.bumptech.glide.Glide
import com.dicoding.picodiploma.gitspot.api.ApiConfig
import com.dicoding.picodiploma.gitspot.data.GitHubResponseTwo
import com.dicoding.picodiploma.gitspot.data.UserData
import com.dicoding.picodiploma.gitspot.databinding.ActivityDetailBinding
import com.dicoding.picodiploma.gitspot.viewmodel.UserViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_USER = "extra_user"
        const val EXTRA_USERNAME = "extra_username"
    }

    private lateinit var binding: ActivityDetailBinding
    private val userVm: UserViewModel by viewModels()

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val bundle = intent.getBundleExtra(EXTRA_USER)
        val user = bundle?.getParcelable(EXTRA_USER, UserData::class.java)

        supportActionBar?.apply {
            title = resources.getString(R.string.detail_user)
            setDisplayHomeAsUpEnabled(true)
        }

        val username = intent.getStringExtra(EXTRA_USERNAME)

        user?.let { userVm.setUserDetail(it.login) }

        userVm.userDetail.observe(this){
            if(it != null){
                binding.apply {
                    Glide.with(this@DetailActivity)
                        .load(it.avatarUrl)
                        .into(ivAvatar)
                    tvName.text = it.name
                    tvUsername.text = it.login
                    tvFollowingCount.text = it.following.toString()
                    tvFollowerCount.text = it.followers.toString()
                }
            }
        }

//        if (username != null) {
//            val call: Call<GitHubResponseTwo> = ApiConfig.getApiService().getUserDetail(username)
//            call.enqueue(object : Callback<GitHubResponseTwo> {
//                override fun onResponse(call: Call<GitHubResponseTwo>, response: Response<GitHubResponseTwo>) {
//                    if (response.isSuccessful) {
//                        val user = response.body()
//                        if (user != null) {
//                            binding.apply {
//                                Glide.with(this@DetailActivity)
//                                    .load(user.avatarUrl)
//                                    .into(ivAvatar)
//                                tvName.text = user.name
//                                tvUsername.text = user.login
//                                tvFollowingCount.text = user.following.toString()
//                                tvFollowerCount.text = user.followers.toString()
//                            }
//                        }
//                    } else {
//                        // handle error response
//                    }
//                }
//
//                override fun onFailure(call: Call<GitHubResponseTwo>, t: Throwable) {
//                    // handle failure
//                }
//            })
//        }
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