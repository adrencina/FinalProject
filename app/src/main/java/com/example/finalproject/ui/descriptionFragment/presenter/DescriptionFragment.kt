package com.example.finalproject.ui.descriptionFragment.presenter

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.replace
import androidx.navigation.fragment.findNavController
import com.example.finalproject.R
import com.example.finalproject.databinding.FragmentCommentsBinding
import com.example.finalproject.databinding.FragmentDescriptionBinding
import com.example.finalproject.ui.commentsFragment.CommentsFragment

import com.example.finalproject.ui.financingFragment.FinancingFragment
import com.example.finalproject.ui.imagesFragment.ImagesFragment

class DescriptionFragment : Fragment() {
    private lateinit var binding: FragmentDescriptionBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDescriptionBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.tvImagesFragment.setOnClickListener {
            findNavController().navigate(R.id.action_descriptionFragment_to_imagesFragment)
        }
        binding.tvFinancingFragment.setOnClickListener {
            findNavController().navigate(R.id.action_descriptionFragment_to_financingFragment)
        }
        binding.tvCommentsFragment.setOnClickListener {
            findNavController().navigate(R.id.action_descriptionFragment_to_commentsFragment)
        }
    }
}