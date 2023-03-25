package com.dicoding.picodiploma.gitspot.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
    val id: Int,
    val login: String,
    val avatarUrl: String,
    val name : String,
    val username : String,
    val following : String,
    val follower : String
) : Parcelable
