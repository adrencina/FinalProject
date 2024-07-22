package com.example.finalproject.ui.commentsFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.finalproject.R
import com.example.finalproject.databinding.FragmentCommentsBinding

class CommentsFragment : Fragment() {
    private lateinit var binding: FragmentCommentsBinding
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
}