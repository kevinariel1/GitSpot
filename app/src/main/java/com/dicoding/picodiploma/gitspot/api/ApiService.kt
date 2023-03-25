package com.dicoding.picodiploma.gitspot.api

import com.dicoding.picodiploma.gitspot.data.GitHubResponse
import com.dicoding.picodiploma.gitspot.data.GitHubResponseTwo
import com.dicoding.picodiploma.gitspot.data.User
import retrofit2.Call
import retrofit2.http.*

interface ApiService {
    @Headers("Authorization: token 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")
    @GET("search/users")
    fun searchUsers(@Query("q") query: String): Call<GitHubResponse>

    @Headers("Authorization: token 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")
    @GET("users/{username}")
    fun getUserDetail(@Path("username") username: String): Call<GitHubResponseTwo>

    @Headers("Authorization: token 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")
    @GET("users/{username}/followers")
    fun getFollowers(@Path("username") username: String): Call<List<User>>

    @Headers("Authorization: token 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")
    @GET("users/{username}/following")
    fun getFollowing(@Path("username") username: String): Call<List<User>>
}