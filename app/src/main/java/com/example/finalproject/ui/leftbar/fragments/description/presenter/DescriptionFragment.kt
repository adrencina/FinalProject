package com.example.finalproject.ui.leftbar.fragments.description.presenter

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.finalproject.R
import com.example.finalproject.data.dto.response.Product
import com.example.finalproject.databinding.FragmentDescriptionBinding
import com.example.finalproject.ui.leftbar.fragments.description.state.DescriptionState
import com.example.finalproject.ui.leftbar.fragments.description.viewModel.DescriptionViewModel

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navigateToFragment()
        observeViewModel()

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
                    showDescription(product = state.data)
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
    private fun showDescription(product: Product){
        binding.tvTitleProduct.text = product.name
        binding.tvDescription.text = product.description
        binding.tvPriceProduct.text = product.price.toString()

    }
}