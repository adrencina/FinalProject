package com.example.finalproject.ui.leftbar.fragments.comment.presenter

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
import com.example.finalproject.ui.leftbar.viewModel.sharedViewModel


class CommentsFragment : Fragment() {
    private lateinit var binding: FragmentCommentsBinding
    private val sharedViewModel : sharedViewModel by  activityViewModels()
    private val viewModel : CommentsViewModel by viewModels{
        CommentsViewModelFactory(CommentsRepository(requireContext()))
    }



//    companion object{
//        private const val ARG_PRODUCT_ID = "product_id"
//
//
//
//        fun newInstance(productId : Int) : CommentsFragment{
//            val instanceFragmentComm = CommentsFragment()
//            val args = Bundle()
//            args.putInt(ARG_PRODUCT_ID, productId)
//            instanceFragmentComm.arguments = args
//
//            return  instanceFragmentComm
//        }
//





//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        arguments?.let {
//
//        }
//    }

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
                sharedViewModel.productPrice.observe(viewLifecycleOwner,{ price ->
                    binding.tvPrice.text = "$${price}"
                })

            }
        }


//        val productId = arguments?.getInt(ARG_PRODUCT_ID, -1) ?: -1


//        if (productId != -1) {
//            viewModel.fetchCommentsByProductId(productId)
//
//        }
//
//        if (productId != -1) {
//            sharedViewModel.product.observe(viewLifecycleOwner,{
//                binding.tvPrice.text = "$${it}"
//            })
//            viewModel.getProductById(productId)
//        }




        viewModel.state.observe(viewLifecycleOwner) { state ->
            when (state) {
                is CommentsState.Loading -> showLoading()
                is CommentsState.Success -> showComments(state.comments)
                is CommentsState.Error -> showError()

            }
        }








    binding.BtnBack.setOnClickListener {
            val intent = Intent(activity,HomeActivity::class.java)
            startActivity(intent)
        }

        binding.tvDescriptionFragment.setOnClickListener {
//            fragmentManager?.beginTransaction()?.replace(R.id.nav_graph_fragment, ImagesFragment())?.commit()
            findNavController().navigate(R.id.action_commentsFragment_to_descriptionFragment)
        }
        binding.tvFinancingFragment.setOnClickListener {
            findNavController().navigate(R.id.action_commentsFragment_to_financingFragment)
        }
        binding.tvImagesFragment.setOnClickListener {
            findNavController().navigate(R.id.action_commentsFragment_to_imagesFragment)
//            fragmentManager?.beginTransaction()?.replace(R.id.nav_graph_fragment, ImagesFragment())?.commit()
        }
    }

    private fun showLoading() {
//        binding.progressBar.visibility = View.VISIBLE
//        binding.contentLayout.visibility = View.GONE
//        binding.ivImgError.visibility = View.GONE
        binding.rvComments.visibility = View.GONE

    }
    private fun showComments(comments: List<Comment>) {

        binding.rvComments.visibility = View.VISIBLE

//         Adaptador para los comentarios
        val adapter = CommentsAdapter(comments)
        binding.rvComments.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.rvComments.adapter = adapter
    }

    private fun showError() {
        binding.rvComments.visibility = View.GONE

    }


}