package com.dicoding.picodiploma.gitspot

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dicoding.picodiploma.gitspot.databinding.ItemUserBinding

class UserAdapter(private val userList: List<User>) :
    RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val binding =
            ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = userList[position]
        holder.bind(user)
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    inner class UserViewHolder(private val binding: ItemUserBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(user: User) {
            binding.tvUsername.text = user.username
            binding.tvName.text = user.name ?: ""
            binding.tvFollowers.text = user.followersCount?.toString() ?: ""
            binding.tvFollowing.text = user.followingCount?.toString() ?: ""
            Glide.with(binding.root.context)
                .load(user.avatarUrl)
                .circleCrop()
                .placeholder(R.drawable.ic_person)
                .into(binding.ivAvatar)
        }
    }
}