package com.dicoding.picodiploma.gitspot

data class User(
    val username: String,
    val name: String?,
    val avatarUrl: String?,
    val followersCount: Int?,
    val followingCount: Int?,
)
