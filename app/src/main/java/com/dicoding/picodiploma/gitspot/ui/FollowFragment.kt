package com.dicoding.picodiploma.gitspot.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.picodiploma.gitspot.adapter.UserAdapter
import com.dicoding.picodiploma.gitspot.databinding.FragmentFollowBinding
import com.dicoding.picodiploma.gitspot.viewmodel.UserViewModel

class FollowFragment : Fragment() {

    private lateinit var binding : FragmentFollowBinding

    private var position: Int = POSITION_FOLLOWER
    private var username: String = ""
    private val userVm: UserViewModel by viewModels()
    private var adapter: UserAdapter = UserAdapter()

    companion object {
        const val ARG_POSITION = "position"
        const val ARG_USERNAME = "username"
        const val POSITION_FOLLOWER = 1
        const val POSITION_FOLLOWING = 2
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFollowBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val layoutManager = LinearLayoutManager(requireActivity())
        binding.rvFollow.layoutManager = LinearLayoutManager(requireActivity())
        binding.rvFollow.adapter = adapter
        val itemDecoration = DividerItemDecoration(requireActivity(), layoutManager.orientation)
        binding.rvFollow.addItemDecoration(itemDecoration)

        userVm.userFollow.observe(viewLifecycleOwner){ submitList ->
            submitList.gitHubResponseThree?.let { adapter.submitList(it) }
            binding.rvFollow.adapter = adapter
        }

        arguments?.let {
            position = it.getInt(ARG_POSITION)
            username = it.getString(ARG_USERNAME) ?: ""
        }
        if (position == POSITION_FOLLOWER){
            userVm.getUserFollowers(username)
        } else {
            userVm.getUserFollowing(username)
        }
    }

}