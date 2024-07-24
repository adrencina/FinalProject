package com.example.finalproject.ui.leftbar.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.finalproject.R
import com.example.finalproject.databinding.FragmentDescriptionBinding
import com.example.finalproject.ui.descriptionFragment.state.DescriptionState
import com.example.finalproject.ui.descriptionFragment.viewModel.DescriptionViewModel

class DescriptionFragment : Fragment() {
    private lateinit var binding: FragmentDescriptionBinding
    val descriptionViewModel by viewModels<DescriptionViewModel>()
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

        navigateToFragment()
        observeViewModel()
        showDescription()
    }

        private fun navigateToFragment() {
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

    private fun observeViewModel() {
        descriptionViewModel.descriptionState.observe(viewLifecycleOwner) { state ->
            when (state) {
                is DescriptionState.Success -> {
                    // Manejar el estado de éxito aquí
                }
                is DescriptionState.Error -> {
                    // Manejar el estado de error aquí
                }
                is DescriptionState.Loading -> {
                    // Manejar el estado de carga aquí
                }
            }
        }



    }
    fun showDescription(){
//        binding.tvTitleProduct.text
//        binding.tvDescription.text
//        binding.tvPriceProduct.text

    }
}