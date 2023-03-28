package com.dicoding.picodiploma.gitspot

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import com.bumptech.glide.Glide
import com.dicoding.picodiploma.gitspot.api.ApiConfig
import com.dicoding.picodiploma.gitspot.data.GitHubResponseTwo
import com.dicoding.picodiploma.gitspot.data.UserData
import com.dicoding.picodiploma.gitspot.databinding.ActivityDetailBinding
import com.dicoding.picodiploma.gitspot.viewmodel.UserViewModel
import de.hdodenhof.circleimageview.CircleImageView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private const val TAG = "DetailActivity"
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
        val user = intent.getParcelableExtra<UserData>(EXTRA_USER)
        Log.d(TAG, "parcelable: $user")
        user?.let {
            userVm.getUserDetail(it.login!!)
        }

        supportActionBar?.apply {
            title = resources.getString(R.string.detail_user)
            setDisplayHomeAsUpEnabled(true)
        }

//        val username = intent.getStringExtra(EXTRA_USERNAME)

        userVm.userDetail.observe(this) { userDetail ->
            // update the UI with the user detail
            Log.d("TAG", "onCreate: $userDetail")
            binding.tvName.text = userDetail.name
            binding.tvUsername.text = userDetail.login
            binding.tvFollowingCount.text = userDetail.following.toString()
            binding.tvFollowerCount.text = userDetail.followers.toString()
            // ...
        }

        // get the user detail from the ViewModel
//        if (username != null) {
//
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