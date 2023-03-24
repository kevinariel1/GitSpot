package com.dicoding.picodiploma.gitspot.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dicoding.picodiploma.gitspot.data.User
import com.dicoding.picodiploma.gitspot.data.UserData
import com.dicoding.picodiploma.gitspot.databinding.ItemUserBinding

class UserAdapter(private val userList: List<UserData?>): RecyclerView.Adapter<UserAdapter.ViewHolder>(){

    private lateinit var listener: OnItemClickListener

    interface OnItemClickListener{
        fun onClick(user: UserData)
    }

    fun setOnClickListener(listener: OnItemClickListener){
        this.listener = listener
    }

    inner class ViewHolder(private val binding: ItemUserBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(user: UserData){
            binding.apply {
                tvUsername.text = user.login
                Glide.with(binding.root)
                    .load(user.avatarUrl)
                    .into(ivAvatar)
                root.setOnClickListener { listener.onClick(user) }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        userList[position]?.let { holder.bind(it) }
    }

    override fun getItemCount(): Int = userList.size

}