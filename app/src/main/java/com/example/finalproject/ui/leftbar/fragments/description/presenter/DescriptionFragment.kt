package com.example.finalproject.ui.leftbar.fragments.description.presenter

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.finalproject.R
import com.example.finalproject.data.dto.response.Product
import com.example.finalproject.data.repository.HomeRepository
import com.example.finalproject.data.repository.LeftbarRepository
import com.example.finalproject.data.service.dto.Utils.ID_PRODUCT
import com.example.finalproject.databinding.FragmentDescriptionBinding
import com.example.finalproject.ui.home.viewModel.HomeViewModel
import com.example.finalproject.ui.home.viewModel.HomeViewModelFactory
import com.example.finalproject.ui.leftbar.fragments.description.state.DescriptionState
import com.example.finalproject.ui.leftbar.fragments.description.viewModel.DescriptionViewModel
import com.example.finalproject.ui.leftbar.fragments.description.viewModel.DescriptionViewModelFactory
import com.example.finalproject.ui.leftbar.presenter.LeftBarActivity

class DescriptionFragment : Fragment() {
    private lateinit var binding: FragmentDescriptionBinding
    private lateinit var descriptionViewModel: DescriptionViewModel
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
        val repository = LeftbarRepository(requireContext())
        descriptionViewModel =
            ViewModelProvider(
                this,
                DescriptionViewModelFactory(repository)
            )[DescriptionViewModel::class.java]

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navigateToFragment()
        observeViewModel()
        descriptionViewModel.fetchProductById(ID_PRODUCT ?: 0)

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

    private fun showDescription(product: Product) {
        binding.tvTitleProduct.text = product.name
        binding.tvDescription.text = product.description
        binding.tvPriceProduct.text = product.price.toString()

    }
}