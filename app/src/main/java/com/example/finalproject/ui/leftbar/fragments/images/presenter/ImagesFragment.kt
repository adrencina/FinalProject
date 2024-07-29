package com.example.finalproject.ui.leftbar.fragments.images.presenter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.finalproject.R
import com.example.finalproject.data.dto.response.Product
import com.example.finalproject.data.repository.LeftbarRepository
import com.example.finalproject.databinding.FragmentImagesBinding
import com.example.finalproject.ui.leftbar.fragments.images.adapter.ImagesAdapter
import com.example.finalproject.ui.leftbar.fragments.images.state.ImagesState
import com.example.finalproject.ui.leftbar.fragments.images.viewModel.ImagesViewModel
import com.example.finalproject.ui.leftbar.fragments.images.viewModel.ImagesViewModelFactory
import com.example.finalproject.ui.leftbar.viewModel.SharedViewModel

class ImagesFragment : Fragment() {

    private lateinit var binding: FragmentImagesBinding
    private val sharedViewModel: SharedViewModel by activityViewModels()
    private val viewModel: ImagesViewModel by viewModels {
        ImagesViewModelFactory(LeftbarRepository(requireContext()))
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentImagesBinding.inflate(inflater, container, false)
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
    }

    private fun navigateToFragment() {
        binding.tvDescriptionFragment.setOnClickListener {
            findNavController().navigate(R.id.action_imagesFragment_to_descriptionFragment)
        }
        binding.tvFinancingFragment.setOnClickListener {
            findNavController().navigate(R.id.action_imagesFragment_to_financingFragment)
        }
        binding.tvCommentsFragment.setOnClickListener {
            findNavController().navigate(R.id.action_imagesFragment_to_commentsFragment)
        }
    }

    private fun observeViewModel() {
        viewModel.state.observe(viewLifecycleOwner) { state ->
            when (state) {
                is ImagesState.Loading -> showLoading()
                is ImagesState.Success -> showProductDetails(state.product)
                is ImagesState.Error -> showError()
            }
        }
    }

    private fun showLoading() {
        binding.ivImgError.visibility = View.GONE
    }

    private fun showProductDetails(product: Product) {
        binding.ivImgError.visibility = View.GONE
        binding.tvNameProduct.text = product.name

        val adapter = ImagesAdapter(product.images ?: emptyList())
        binding.rvImgfragment.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.rvImgfragment.adapter = adapter
    }

    private fun showError() {
        binding.ivImgError.visibility = View.VISIBLE
        binding.ivIcError.visibility = View.VISIBLE
//        binding.tvErrorMessage.text = getString(R.string.error_message)
    }
}