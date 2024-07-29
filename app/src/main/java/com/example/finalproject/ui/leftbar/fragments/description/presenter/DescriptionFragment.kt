package com.example.finalproject.ui.leftbar.fragments.description.presenter

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.finalproject.R
import com.example.finalproject.data.dto.response.Product
import com.example.finalproject.data.repository.LeftbarRepository
import com.example.finalproject.databinding.FragmentDescriptionBinding
import com.example.finalproject.ui.home.presenter.HomeActivity
import com.example.finalproject.ui.leftbar.fragments.description.state.DescriptionState
import com.example.finalproject.ui.leftbar.fragments.description.viewModel.DescriptionViewModel
import com.example.finalproject.ui.leftbar.fragments.description.viewModel.DescriptionViewModelFactory
import com.example.finalproject.ui.leftbar.viewModel.SharedViewModel

class DescriptionFragment : Fragment() {

    private lateinit var binding: FragmentDescriptionBinding
    private val sharedViewModel: SharedViewModel by activityViewModels()
    private val viewModel: DescriptionViewModel by viewModels {
        DescriptionViewModelFactory(LeftbarRepository(requireContext()))
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

        sharedViewModel.productId.observe(viewLifecycleOwner) { id ->
            if (id != -1) {
                viewModel.fetchProductById(id)
                observeViewModel()
            }
        }

        sharedViewModel.productPrice.observe(viewLifecycleOwner) { price ->
            binding.tvPriceProduct.text = "$${price}"
        }

        navigateToFragment()

        binding.BtnBack.setOnClickListener {
            val intent = Intent(activity, HomeActivity::class.java)
            startActivity(intent)
        }
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
        viewModel.descriptionState.observe(viewLifecycleOwner) { state ->
            when (state) {
                is DescriptionState.Loading -> showLoading()
                is DescriptionState.Success -> showDescription(state.data)
                is DescriptionState.Error -> showError()
            }
        }
    }

    private fun showLoading() {
        // estado de carga
    }

    private fun showDescription(product: Product) {
        binding.tvTitleProduct.text = product.name
        binding.tvDescription.text = product.description
        binding.tvPriceProduct.text = product.price.toString()
    }

    private fun showError() {
        // estado de error
    }
}