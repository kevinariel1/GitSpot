package com.dicoding.picodiploma.gitspot

import retrofit2.Call
import retrofit2.http.*

interface ApiService {
    @Headers("Authorization: token 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")
    @GET("search/users")
    fun searchUsers(@Query("q") query: String): Call<GitHubResponse>

    @Headers("Authorization: token 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")
    @GET("users/{username}")
    fun getUserDetail(@Path("username") username: String): Call<User>

    @Headers("Authorization: token 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")
    @GET("users/{username}/followers")
    fun getFollowers(@Path("username") username: String): Call<List<User>>

    @Headers("Authorization: token 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")
    @GET("users/{username}/following")
    fun getFollowing(@Path("username") username: String): Call<List<User>>
}