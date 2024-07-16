package com.example.finalproject

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.finalproject.databinding.FragmentDescriptionBinding
import com.example.finalproject.ui.descriptionFragment.viewModel.DescriptionViewModel


class DescriptionFragment : Fragment() {
    private lateinit var binding: FragmentDescriptionBinding
    private val descriptionViewModel by viewModels<DescriptionViewModel>()


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


}