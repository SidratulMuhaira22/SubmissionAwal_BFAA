package com.sidratul.submission1bfaa.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.sidratul.submission1bfaa.R
import com.sidratul.submission1bfaa.adapter.UserAdapter
import com.sidratul.submission1bfaa.databinding.FragmentFollowingBinding

class FollowingFragment : Fragment() {

    private val detailViewModel: DetailViewModel by activityViewModels()
    private var _binding: FragmentFollowingBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentFollowingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val layoutManager = LinearLayoutManager(requireActivity())
        binding.listUsers.layoutManager = layoutManager

        val itemDecoration = DividerItemDecoration(requireActivity(), layoutManager.orientation)
        binding.listUsers.addItemDecoration(itemDecoration)

        detailViewModel.userFollowing.observe(viewLifecycleOwner) {
            binding.listUsers.adapter = UserAdapter(it)
            showLoading(false)
        }
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progrerssBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onResume() {
        super.onResume()
        binding.root.requestLayout()
    }

    companion object {
        private const val TAG = "FollowingFragment"
    }
}