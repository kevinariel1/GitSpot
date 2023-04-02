package com.dicoding.picodiploma.gitspot.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dicoding.picodiploma.gitspot.data.*
import com.dicoding.picodiploma.gitspot.databinding.ItemUserBinding

class UserAdapterTwo() : RecyclerView.Adapter<UserAdapterTwo.ViewHolder>() {

    private var followerList : List<GitHubResponseThreeItem?>? = null

    inner class ViewHolder(private val binding: ItemUserBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(user: GitHubResponseThreeItem) {
            binding.apply {
                tvUsername.text = user.login
                tvName.text = user.login
                tvFollowingCount.text = user.followingUrl
                tvFollowerCount.text = user.followersUrl
                Glide.with(binding.root)
                    .load(user.avatarUrl)
                    .into(ivAvatar)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        followerList?.get(position)?.let { holder.bind(it) }
    }

    override fun getItemCount(): Int = followerList?.size ?: 0

    fun setList(list: List<GitHubResponseThreeItem?>) {
        followerList = list
    }
}