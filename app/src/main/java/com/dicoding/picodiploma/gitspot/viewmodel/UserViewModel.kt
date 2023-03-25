package com.dicoding.picodiploma.gitspot.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dicoding.picodiploma.gitspot.data.GitHubResponse
import com.dicoding.picodiploma.gitspot.api.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserViewModel : ViewModel() {
    companion object{
        private const val TAG = "UserViewModel"
    }

    private var _userResponse: MutableLiveData<GitHubResponse> = MutableLiveData()
    val users: LiveData<GitHubResponse> = _userResponse

    init {
        searchUser("Michael")
    }


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
}