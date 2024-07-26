package com.example.finalproject.ui.leftbar.fragments

import LeftbarViewModel
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.finalproject.R
import com.example.finalproject.data.repository.LeftbarRepository
import com.example.finalproject.databinding.FragmentImagesBinding
import com.example.finalproject.ui.leftbar.viewModel.LeftbarViewModelFactory
import com.example.finalproject.ui.leftbar.viewModel.ProductIdListener

class ImagesFragment : Fragment(), ProductIdListener {

    private lateinit var binding: FragmentImagesBinding
    private val viewModel: LeftbarViewModel by viewModels {
        LeftbarViewModelFactory(LeftbarRepository(requireContext()))
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
//
//        val productId = arguments?.getInt("idProduct") ?: 0
//
//        viewModel.product.observe(viewLifecycleOwner, Observer { product ->
//            product?.let {
//                binding.tvNameProduct.text = it.name ?: "Nombre no disponible"
//                binding.tvPriceProduct.text = it.price.toString() ?: "Precio no disponible"
//            }
//        })
//
//        viewModel.fetchProductById(productId)


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

    override fun onProductIdReceived(productId: Int) {
        binding.tvNameProduct.text = "ID del producto recibido: $productId"
    }
}