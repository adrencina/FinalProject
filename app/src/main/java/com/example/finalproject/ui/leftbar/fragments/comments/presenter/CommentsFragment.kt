package com.example.finalproject.ui.leftbar.fragments.comments.presenter

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.finalproject.data.dto.model.Comment
import com.example.finalproject.data.repository.LeftbarRepository
import com.example.finalproject.databinding.FragmentCommentsBinding
import com.example.finalproject.ui.leftbar.fragments.comments.state.CommentsState
import com.example.finalproject.ui.leftbar.fragments.comments.viewModel.CommentsViewModel
import com.example.finalproject.ui.leftbar.fragments.comments.viewModel.CommentsViewModelFactory

class CommentsFragment : Fragment() {

    private lateinit var binding: FragmentCommentsBinding
    private val viewModel: CommentsViewModel by viewModels {
        CommentsViewModelFactory(LeftbarRepository(requireContext()))
    }

    companion object {
        private const val ARG_PRODUCT_ID = "product_id"

        fun newInstance(productId: Int): CommentsFragment {
            val instanceFragment = CommentsFragment()
            val args = Bundle()
            args.putInt(ARG_PRODUCT_ID, productId)
            instanceFragment.arguments = args
            return instanceFragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentCommentsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val productId = arguments?.getInt(ARG_PRODUCT_ID, -1) ?: -1

        if (productId != -1) {
            viewModel.fetchCommentsByProductId(productId)
        }

        viewModel.state.observe(viewLifecycleOwner) { state ->
            when (state) {
                is CommentsState.Loading -> showLoading()
                is CommentsState.Success -> showComments(state.comments)
                is CommentsState.Error -> showError(state.message)
            }
        }
    }

    private fun showLoading() {
//        binding.progressBar.visibility = View.VISIBLE
//        binding.contentLayout.visibility = View.GONE
//        binding.errorLayout.visibility = View.GONE
    }

    private fun showComments(comments: List<Comment>) {
//        binding.progressBar.visibility = View.GONE
//        binding.contentLayout.visibility = View.VISIBLE
//        binding.errorLayout.visibility = View.GONE




        // Adaptador para los comentarios
//        val adapter = CommentsAdapter(comments)
//        binding.recyclerViewComments.adapter = adapter
    }

    private fun showError(message: String) {
//        binding.progressBar.visibility = View.GONE
//        binding.contentLayout.visibility = View.GONE
//        binding.errorLayout.visibility = View.VISIBLE
//        binding.errorMessage.text = message
    }
}