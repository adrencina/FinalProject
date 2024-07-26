package com.example.finalproject.ui.leftbar.fragments.images.presenter

import LeftbarViewModel
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.finalproject.data.repository.LeftbarRepository
import com.example.finalproject.databinding.FragmentImagesBinding
import com.example.finalproject.ui.leftbar.viewModel.LeftbarViewModelFactory

class ImagesFragment : Fragment() {

    private lateinit var binding: FragmentImagesBinding
    private val viewModel: LeftbarViewModel by viewModels {
        LeftbarViewModelFactory(LeftbarRepository(requireContext()))
    }

    companion object {
        private const val ARG_PRODUCT_ID = "product_id"

        fun newInstance(productId: Int): ImagesFragment {
            val instanceFragmentImg = ImagesFragment()
            val args = Bundle()
            args.putInt(ARG_PRODUCT_ID, productId)
            instanceFragmentImg.arguments = args
            return instanceFragmentImg
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentImagesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val productId = arguments?.getInt(ARG_PRODUCT_ID, -1) ?: -1

        if (productId != -1) {
            viewModel.fetchProductById(productId)
        }

        // Observa los cambios en el producto
        viewModel.product.observe(viewLifecycleOwner) { product ->
            binding.tvNameProduct.text = product.name
            binding.tvPriceProduct.text = product.price.toString()
        }

    }
}