package com.dicoding.picodiploma.gitspot.api

import com.dicoding.picodiploma.gitspot.data.*
import retrofit2.Call
import retrofit2.http.*

interface ApiService {
    @GET("search/users")
    fun searchUsers(@Query("q") query: String): Call<GitHubResponse>

    @GET("users/{username}")
    fun getUserDetail(@Path("username") username: String): Call<GitHubResponseTwo>

    @GET("users/{username}/followers")
    fun getFollowers(@Path("username") username: String): Call<GitHubResponseThree>

    @GET("users/{username}/following")
    fun getFollowing(@Path("username") username: String): Call<GitHubResponseThree>
}