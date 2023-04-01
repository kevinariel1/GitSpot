package com.dicoding.picodiploma.gitspot.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dicoding.picodiploma.gitspot.data.GitHubResponse
import com.dicoding.picodiploma.gitspot.api.ApiConfig
import com.dicoding.picodiploma.gitspot.data.GitHubResponseThree
import com.dicoding.picodiploma.gitspot.data.GitHubResponseThreeItem
import com.dicoding.picodiploma.gitspot.data.GitHubResponseTwo
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserViewModel : ViewModel() {
    companion object{
        private const val TAG = "UserViewModel"
    }

    private var _userResponse: MutableLiveData<GitHubResponse> = MutableLiveData()
    private var _userDetail: MutableLiveData<GitHubResponseTwo> = MutableLiveData()
    private var _userFollow : MutableLiveData<GitHubResponseThree> = MutableLiveData()
    val users: LiveData<GitHubResponse> = _userResponse
    val userDetail : LiveData<GitHubResponseTwo> = _userDetail
    val userFollow : LiveData<GitHubResponseThree> = _userFollow

    fun searchUser(q: String){
        ApiConfig.getApiService().searchUsers(q).enqueue(object :Callback<GitHubResponse>{
            override fun onResponse(
                call: Call<GitHubResponse>,
                response: Response<GitHubResponse>,
            ) {
                if(response.isSuccessful){
                    _userResponse.postValue(response.body())
                }
            }

            override fun onFailure(call: Call<GitHubResponse>, t: Throwable) {
                Log.d(TAG, "onFailure: ${t.localizedMessage}")
            }

        })
    }

    fun getUserDetail(username: String) {
        ApiConfig.getApiService().getUserDetail(username).enqueue(object : Callback<GitHubResponseTwo> {
            override fun onResponse(call: Call<GitHubResponseTwo>, response: Response<GitHubResponseTwo>) {
                if (response.isSuccessful) {
                    _userDetail.postValue(response.body())
                }
            }

            override fun onFailure(call: Call<GitHubResponseTwo>, t: Throwable) {
                // handle error
            }
        })
    }

    fun getUserFollowers(username: String){
        Log.d("UserViewModel", "getUserFollowers called with username: $username")
        ApiConfig.getApiService().getFollowers(username).enqueue(object : Callback<GitHubResponseThree>{
            override fun onResponse(call: Call<GitHubResponseThree>, response: Response<GitHubResponseThree>
            ) {
                if(response.isSuccessful){
                    _userFollow.postValue(response.body())
                }
            }

            override fun onFailure(call: Call<GitHubResponseThree>, t: Throwable) {
                // handle error
            }
        })
    }

    fun getUserFollowing(username: String){
        Log.d("UserViewModel", "getUserFollowing called with username: $username")
        ApiConfig.getApiService().getFollowing(username).enqueue(object : Callback<GitHubResponseThree>{
            override fun onResponse(call: Call<GitHubResponseThree>, response: Response<GitHubResponseThree>
            ) {
                if(response.isSuccessful){
                    _userFollow.postValue(response.body())
                }
            }

            override fun onFailure(call: Call<GitHubResponseThree>, t: Throwable) {
                // handle error
            }
        })
    }
}