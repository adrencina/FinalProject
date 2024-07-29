package com.example.finalproject.ui.leftbar.fragments.comment.presenter

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.finalproject.R
import com.example.finalproject.data.dto.model.Comment
import com.example.finalproject.data.service.CommentsRepository
import com.example.finalproject.databinding.FragmentCommentsBinding
import com.example.finalproject.ui.home.presenter.HomeActivity
import com.example.finalproject.ui.leftbar.fragments.comment.adapter.CommentsAdapter
import com.example.finalproject.ui.leftbar.fragments.comment.state.CommentsState
import com.example.finalproject.ui.leftbar.fragments.comment.viewModel.CommentsViewModel
import com.example.finalproject.ui.leftbar.fragments.comment.viewModel.CommentsViewModelFactory
import com.example.finalproject.ui.leftbar.viewModel.SharedViewModel

class CommentsFragment : Fragment() {

    private lateinit var binding: FragmentCommentsBinding
    private val sharedViewModel: SharedViewModel by activityViewModels()
    private val viewModel: CommentsViewModel by viewModels {
        CommentsViewModelFactory(CommentsRepository(requireContext()))
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCommentsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sharedViewModel.productId.observe(viewLifecycleOwner) { id ->
            if (id != -1) {
                viewModel.fetchCommentsByProductId(id)
            }
        }

        sharedViewModel.productPrice.observe(viewLifecycleOwner) { price ->
            binding.tvPrice.text = "$${price}"
        }

        observeViewModel()
        setupNavigation()

        binding.BtnBack.setOnClickListener {
            val intent = Intent(activity, HomeActivity::class.java)
            startActivity(intent)
        }
    }

    private fun setupNavigation() {
        binding.tvDescriptionFragment.setOnClickListener {
            findNavController().navigate(R.id.action_commentsFragment_to_descriptionFragment)
        }
        binding.tvFinancingFragment.setOnClickListener {
            findNavController().navigate(R.id.action_commentsFragment_to_financingFragment)
        }
        binding.tvImagesFragment.setOnClickListener {
            findNavController().navigate(R.id.action_commentsFragment_to_imagesFragment)
        }
    }

    private fun observeViewModel() {
        viewModel.state.observe(viewLifecycleOwner) { state ->
            when (state) {
                is CommentsState.Loading -> showLoading()
                is CommentsState.Success -> showComments(state.comments)
                is CommentsState.Error -> showError()
            }
        }
    }

    private fun showLoading() {
        binding.rvComments.visibility = View.GONE
    }

    private fun showComments(comments: List<Comment>) {
        binding.rvComments.visibility = View.VISIBLE
        val adapter = CommentsAdapter(comments)
        binding.rvComments.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.rvComments.adapter = adapter
    }

    private fun showError() {
        binding.rvComments.visibility = View.GONE
    }
}