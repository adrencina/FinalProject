package com.example.finalproject.ui.commentsFragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentResultListener
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.findNavController
import com.example.finalproject.R
import com.example.finalproject.databinding.FragmentCommentsBinding
import com.example.finalproject.ui.financingFragment.FinancingFragment
import com.example.finalproject.ui.imagesFragment.ImagesFragment
import java.security.acl.Owner

class CommentsFragment : Fragment() {
    private lateinit var binding: FragmentCommentsBinding
    private val viewModel: CommentViewModel by viewModels()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {


        }
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


        viewModel.comment.observe(viewLifecycleOwner, Observer { comment ->
            if (!comment.isNullOrEmpty()){

            }
        })


        binding.tvImagesFragment.setOnClickListener {
            findNavController().navigate(R.id.action_commentsFragment_to_imagesFragment)
        }
        binding.tvFinancingFragment.setOnClickListener {
            findNavController().navigate(R.id.action_commentsFragment_to_financingFragment)
        }

        binding.tvDescriptionFragment.setOnClickListener {
            findNavController().navigate(R.id.action_commentsFragment_to_descriptionFragment)
        }
    }
}

